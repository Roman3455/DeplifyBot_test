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

    public static EditMessageText ofMarkdown(
            @Nonnull final Long chatId,
            @Nonnull final Long messageId,
            @Nonnull final String text,
            final ReplyMarkup replyMarkup
    ) {
        return new EditMessageText(chatId, messageId, text, ParseModeType.MARKDOWN_V2, replyMarkup);
    }

    public static EditMessageText ofHtml(
            @Nonnull final Long chatId,
            @Nonnull final Long messageId,
            @Nonnull final String text,
            final ReplyMarkup replyMarkup
    ) {
        return new EditMessageText(chatId, messageId, text, ParseModeType.HTML, replyMarkup);
    }

    public static EditMessageText ofPlain(
            @Nonnull final Long chatId,
            @Nonnull final Long messageId,
            @Nonnull final String text,
            final ReplyMarkup replyMarkup
    ) {
        return new EditMessageText(chatId, messageId, text, ParseModeType.PLAIN, replyMarkup);
    }
}
