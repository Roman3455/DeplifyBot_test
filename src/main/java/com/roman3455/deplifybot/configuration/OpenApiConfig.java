package com.roman3455.deplifybot.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI botOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Deplify Bot API")
                        .description("API для Telegram-бота Deplify — документация для разработчиков")
                        .version("0.1.0")
                        .contact(new Contact()
                                .name("Roman Smirnov")
                                .email("s.roman3455@gmail.com")
                                .url("https://github.com/Roman3455"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }
}
