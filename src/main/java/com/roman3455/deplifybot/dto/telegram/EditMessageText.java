package com.roman3455.deplifybot.dto.telegram;

import jakarta.annotation.Nonnull;

import java.util.Objects;

public record EditMessageText(
        Long chatId,
        Long messageId,
        String inlineMessageId,
        @Nonnull String text,
        String parseMode,
        ReplyMarkup replyMarkup
) {

    public EditMessageText {
        Objects.requireNonNull(text, "Field 'text' must not be null");
    }
}
