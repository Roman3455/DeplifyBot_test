package com.roman3455.deplifybot.dto.telegram.api.request;

import com.roman3455.deplifybot.dto.telegram.api.enumiration.ParseModeType;
import com.roman3455.deplifybot.dto.telegram.api.ui.ReplyMarkup;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;

import java.util.Objects;

public record EditMessageText(
        @Nonnull Long chatId,
        @Nonnull Long messageId,
        @Nonnull String text,
        ParseModeType parseMode,
        @Valid ReplyMarkup replyMarkup
) {

    public EditMessageText {
        Objects.requireNonNull(chatId, "Field 'chatId' cannot be null");
        Objects.requireNonNull(messageId, "Field 'messageId' cannot be null");
        Objects.requireNonNull(text, "Field 'text' cannot be null");
    }
}
