package com.kb.exam.kapuka.infrastructure.openapi.kakao;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class KakaoOpenApiConfiguration {

    @Value("${openapi.kakao.key}")
    private String key;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("Authorization", "KakaoAK " + key);
    }
}
