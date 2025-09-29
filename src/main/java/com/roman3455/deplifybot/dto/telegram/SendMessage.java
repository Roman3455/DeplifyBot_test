package com.roman3455.deplifybot.dto.telegram;

import jakarta.annotation.Nonnull;

import java.util.Objects;

public record SendMessage(
        @Nonnull Object chatId,
        Long messageThreadId,
        @Nonnull String text,
        String parseMode,
        Boolean disableNotification,
        ReplyMarkup replyMarkup
) {

    public static final int MAX_TEXT_LENGTH = 4096;

    public SendMessage {
        Objects.requireNonNull(chatId, "Field 'chatId' must not be null.");
        Objects.requireNonNull(text, "Field 'text' must not be null.");
        if (text.isEmpty() || text.length() > MAX_TEXT_LENGTH) {
            throw new IllegalArgumentException("Allowed 'text' length is 1 to 4096 characters.");
        }
    }
}
