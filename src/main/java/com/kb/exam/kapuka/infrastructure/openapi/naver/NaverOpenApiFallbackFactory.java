package com.kb.exam.kapuka.infrastructure.openapi.naver;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
public class NaverOpenApiFallbackFactory implements FallbackFactory<NaverOpenApiClient> {
    @Override
    public NaverOpenApiClient create(final Throwable cause) {
        return (query, size) -> {
            log.error("[FALLBACK] naver api error, query:`{}`", query, cause);
            return Optional.empty();
        };
    }
}
