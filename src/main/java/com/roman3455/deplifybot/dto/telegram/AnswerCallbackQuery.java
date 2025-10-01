package com.roman3455.deplifybot.dto.telegram;

import jakarta.annotation.Nonnull;

import java.util.Objects;

public record AnswerCallbackQuery(
        @Nonnull String callbackQueryId,
        String text,
        Boolean showAlert,
        Integer cacheTime
) {
    public AnswerCallbackQuery {
        Objects.requireNonNull(callbackQueryId, "Field 'callbackQueryId' must not be null.");
    }

    public static AnswerCallbackQuery defaultAnswerCallbackQuery(String callbackQueryId) {
        return new AnswerCallbackQuery(callbackQueryId, null, null, null);
    }
}
