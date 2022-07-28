package com.kb.exam.kapuka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class KapukaApplication {

    public static void main(String[] args) {
        SpringApplication.run(KapukaApplication.class, args);
    }

}
