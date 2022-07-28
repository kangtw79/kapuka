package com.kb.exam.kapuka.infrastructure.openapi.naver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@FeignClient(name = "openapi.naver",
             url = "${openapi.naver.host}",
             fallbackFactory = NaverOpenApiFallbackFactory.class,
             configuration = NaverOpenApiConfiguration.class)
public interface NaverOpenApiClient {

    @GetMapping("/v1/search/local.json?display={display}")
    Optional<PlaceSearchResultDTO> search(@RequestParam("query") String query, @PathVariable("display") int display);
}
