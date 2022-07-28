package com.kb.exam.kapuka.infrastructure;

import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableFeignClients
@EnableCircuitBreaker
@EnableJpaRepositories
public class InfrastructureConfiguration {

}
