package com.roman3455.deplifybot.dto.telegram.inbound;

public record CallbackQuery(
        String Id,
        User from,
        Message message,
        String inlineMessageId,
        String data
) {
}
