package com.roman3455.deplifybot.dto.telegram.nested;

public record User(
        Long id,
        boolean isBot,
        String firstName,
        String username,
        String languageCode
) {
}
