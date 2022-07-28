package com.kb.exam.kapuka.infrastructure.openapi.kakao;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "openapi.kakao"
        , url = "${openapi.kakao.host}"
        , fallbackFactory = KakaoOpenApiFallbackFactory.class
        , configuration = KakaoOpenApiConfiguration.class)
public interface KakaoOpenApiClient {

    @GetMapping("/v2/local/search/keyword.json?page={page}&size={size}")
    Optional<PlaceSearchResultDTO> search(@RequestParam("query") String query,@PathVariable("page") int page, @PathVariable("size") int size);
}
