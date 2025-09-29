package com.roman3455.deplifybot.dto.telegram.nested;

public record CallbackQuery(
        String Id,
        User from,
        String data
) {
}
