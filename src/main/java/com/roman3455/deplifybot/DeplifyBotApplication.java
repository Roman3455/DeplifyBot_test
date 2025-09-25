package com.roman3455.deplifybot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients
@SpringBootApplication
public class DeplifyBotApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeplifyBotApplication.class, args);
    }

}
