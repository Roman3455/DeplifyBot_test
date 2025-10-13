package com.roman3455.deplifybot.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roman3455.deplifybot.exception.feign.TelegramErrorDecoder;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 */
@Configuration
public class TelegramFeignConfig {

    /**
     */
    @Bean
    ErrorDecoder telegramErrorDecoder(final ObjectMapper objectMapper) {
        return new TelegramErrorDecoder(objectMapper);
    }
}
