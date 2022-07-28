package com.kb.exam.kapuka.domain.search;

import com.kb.exam.kapuka.domain.search.model.PlaceVO;
import com.kb.exam.kapuka.infrastructure.openapi.kakao.KakaoOpenApiClient;
import com.kb.exam.kapuka.infrastructure.openapi.kakao.PlaceSearchDocumentDTO;
import com.kb.exam.kapuka.infrastructure.openapi.naver.NaverOpenApiClient;
import com.kb.exam.kapuka.infrastructure.openapi.naver.PlaceSearchItemDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import static java.util.stream.Collectors.toList;

@Slf4j
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class PlaceSearchServiceImpl implements PlaceSearchService{

    private final KakaoOpenApiClient kakaoOpenApiClient;
    private final NaverOpenApiClient naverOpenApiClient;
    private static final int SEARCH_SIZE = 5;

    @Override
    public List<String> search(String keyword) {
        List<PlaceVO> kakaoList = getKakaoSearch(keyword,1,SEARCH_SIZE).get();
        List<String> result;

        if (kakaoList.size() < SEARCH_SIZE) {
            List<PlaceVO> naverList = getNaverSearch(keyword,SEARCH_SIZE-kakaoList.size()+SEARCH_SIZE).get();
            result = merge(kakaoList,naverList);
        }else {
            List<PlaceVO> naverList = getNaverSearch(keyword, SEARCH_SIZE).get();

            if (naverList.size() < SEARCH_SIZE) {
                List<PlaceVO> kakaoExtraList = getKakaoSearch(keyword, 2, SEARCH_SIZE).get();
                kakaoList.addAll(kakaoExtraList.subList(0, SEARCH_SIZE - naverList.size()));
            }
            result = merge(kakaoList,naverList);
        }
        return result;
    }

    private List<String> merge(List<PlaceVO> kakaoList,List<PlaceVO> naverList){
        kakaoList.addAll(naverList.stream()
                .filter(n -> kakaoList.stream().noneMatch(r -> isSamePlace(n, r)))
                .collect(Collectors.toList()));
        return kakaoList.stream().map(p->p.getTitle()).collect(toList());
    }

    private Optional<List<PlaceVO>> getNaverSearch(String keyword, int size) {
        return naverOpenApiClient.search(keyword,size)
                .map(p->p.getItems().stream()
                        .map(this::apply)
                        .collect(toList()));
    }

    private Optional<List<PlaceVO>> getKakaoSearch(String keyword,int page,int size) {
        return kakaoOpenApiClient.search(keyword,page,size)
                .map(p->p.getDocuments().stream()
                        .map(this::apply)
                        .collect(toList()));
    }

    private PlaceVO apply(PlaceSearchItemDTO dto) {
        log.info("Kakao : {}",dto.toString());
        return new PlaceVO(dto.getTitle().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")
                ,dto.getAddress()
                ,dto.getRoadAddress());
    }

    private PlaceVO apply(PlaceSearchDocumentDTO dto) {
        log.info("naver : {}",dto.toString());
        return new PlaceVO(dto.getName().replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "")
                ,dto.getAddressName()
                ,dto.getRoadAddressName());
    }

    private boolean isSamePlace(PlaceVO local1, PlaceVO local2) {
        if (isSameName(local1.getTitle(), local2.getTitle())) {
            return true;
        }
        if (isSameAddress(local1.getRoadAddress(), local2.getRoadAddress())) {
            return true;
        }
        return isSameAddress(local1.getAddress(), local2.getAddress());
    }

    private boolean isSameName(String name1, String name2) {
        name1 = name1.replaceAll(" ", "");
        name2 = name2.replaceAll(" ", "");
        return name1.equals(name2);
    }

    private boolean isSameAddress(String address1, String address2) {
        Set<String> addressSet1;
        Set<String> addressSet2;

        try {
            addressSet1 = addressConvertToSet(address1);
            addressSet2 = addressConvertToSet(address2);

            if (addressSet1.size() < addressSet2.size()) {
                return addressSet1.stream().allMatch(addressSet2::remove);
            }
        }catch(NullPointerException npe){
            return false;
        }

        return addressSet2.stream().allMatch(addressSet1::remove);
    }

    private Set<String> addressConvertToSet(String address) {
        return Stream.of(address.split(" ")).map(a -> {
            if (!a.matches("[0-9|a-z|A-Z|ㄱ-ㅎ|ㅏ-ㅣ|가-힝]*")) {
                return a;
            }
            char[] aArr = a.toCharArray();
            for (char c : aArr) {
                if (Character.isDigit(c)) {
                    return a;
                }
            }
            return a.length() > 2 ? a.substring(0, 2) : a;
        }).collect(Collectors.toSet());
    }
}
