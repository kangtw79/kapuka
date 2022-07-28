package com.kb.exam.kapuka.infrastructure.openapi.naver;

import feign.RequestInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class NaverOpenApiConfiguration {
    @Value("${openapi.naver.id}")
    private String id;
    @Value("${openapi.naver.secret}")
    private String secret;

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requestTemplate -> requestTemplate.header("X-Naver-Client-Id", id)
                .header("X-Naver-Client-Secret",secret);
    }
}
