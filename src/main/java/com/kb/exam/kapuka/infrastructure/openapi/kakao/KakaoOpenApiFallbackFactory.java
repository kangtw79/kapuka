package com.kb.exam.kapuka.infrastructure.openapi.kakao;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class KakaoOpenApiFallbackFactory implements FallbackFactory<KakaoOpenApiClient> {
    @Override
    public KakaoOpenApiClient create(final Throwable cause) {
        return (query, page, size) -> {
            log.error("[FALLBACK] kakao api error, query:`{}`", query, cause);
            return Optional.empty();
        };
    }
}
