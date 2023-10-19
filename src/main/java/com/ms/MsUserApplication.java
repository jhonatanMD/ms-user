package com.ms;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Slf4j
@Configuration
@SpringBootApplication
@EnableJpaAuditing
public class MsUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(MsUserApplication.class, args);
    }

}
