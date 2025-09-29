package com.roman3455.deplifybot.dto.telegram.nested;

public record ChatShared(
        Long requestId,
        Long chatId,
        String title,
        String username
) {
}
