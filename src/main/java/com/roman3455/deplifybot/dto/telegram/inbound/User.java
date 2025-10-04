package com.roman3455.deplifybot.dto.telegram.inbound;

public record User(
        Long id,
        boolean isBot,
        String firstName,
        String username,
        String languageCode
) {
}
