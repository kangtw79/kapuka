package com.kb.exam.kapuka.domain.search;

import com.kb.exam.kapuka.infrastructure.repository.search.KeywordHistory;
import com.kb.exam.kapuka.infrastructure.repository.search.KeywordHistoryRepository;
import com.kb.exam.kapuka.domain.search.model.RankKeyword;
import com.kb.exam.kapuka.domain.search.model.RankKeywordVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KeywordServiceImpl implements KeywordService {

    private final KeywordHistoryRepository keywordHistoryRepository;

    @Override
    @Transactional(readOnly = false)
    public void save(String keyword) {
        KeywordHistory searchHistory = new KeywordHistory();
        searchHistory.setKeyword(keyword);
        searchHistory.setCreatedAt(new Date());
        keywordHistoryRepository.save(searchHistory);
    }

    @Override
    public List<RankKeywordVO> getRankKeyword(int limit) {
        return keywordHistoryRepository.findKeywordCountByGroupByKeyword(PageRequest.of(0,limit))
                .stream()
                .map(this::appy)
                .collect(Collectors.toList());
    }

    private RankKeywordVO appy(RankKeyword vo) {
        return new RankKeywordVO(vo.getKeyword()
                , vo.getCount());
    }
}
