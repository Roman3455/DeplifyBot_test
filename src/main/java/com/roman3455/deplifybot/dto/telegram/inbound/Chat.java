package com.roman3455.deplifybot.dto.telegram.inbound;

public record Chat(
        Long id,
        String type,
        String title,
        String username,
        String firstName,
        Boolean isForum
) {
}
