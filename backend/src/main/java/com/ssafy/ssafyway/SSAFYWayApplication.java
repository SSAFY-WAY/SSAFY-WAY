package com.ssafy.ssafyway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class SSAFYWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(SSAFYWayApplication.class, args);
    }
}
