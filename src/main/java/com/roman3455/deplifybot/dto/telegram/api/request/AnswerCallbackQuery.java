package com.roman3455.deplifybot.dto.telegram.api.request;

import jakarta.annotation.Nonnull;

import java.util.Objects;

public record AnswerCallbackQuery(
        @Nonnull String callbackQueryId,
        String text
) {

    public AnswerCallbackQuery {
        Objects.requireNonNull(callbackQueryId, "Field 'callbackQueryId' cannot be null.");
    }
}
