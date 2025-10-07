package com.roman3455.deplifybot.dto.telegram.api.request;

import com.roman3455.deplifybot.dto.telegram.api.ui.ReplyMarkup;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;

import java.util.Objects;

public record SendMessage(
        @Nonnull Object chatId,
        Long messageThreadId,
        @Nonnull String text,
        String parseMode,
        Boolean disableNotification,
        @Valid ReplyMarkup replyMarkup
) {

    public static final int MAX_TEXT_LENGTH = 4096;

    public SendMessage {
        Objects.requireNonNull(chatId, "Field 'chatId' cannot be null.");
        Objects.requireNonNull(text, "Field 'text' cannot be null.");
        if (text.isEmpty() || text.length() > MAX_TEXT_LENGTH) {
            throw new IllegalArgumentException("Allowed 'text' length is 1 to 4096 characters.");
        }
    }

    public static SendMessage defaultSilentMarkupMessage(
            @Nonnull final Object chatId,
            @Nonnull final String text,
            final ReplyMarkup replyMarkup
    ) {
        return new SendMessage(chatId, null, text, null, true, replyMarkup);
    }
}
