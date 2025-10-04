package com.roman3455.deplifybot.dto.telegram.inbound;

import jakarta.annotation.Nonnull;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public record InlineKeyboardButton(
        @Nonnull String text,
        String url,
        String callbackData,
        CopyTextButton copyText
) {

    public static final int MAX_CALLBACK_DATA_LENGTH = 64;

    public InlineKeyboardButton {
        Objects.requireNonNull(text, "Field 'text' must not be null.");
        int filledFields = 0;
        if (url != null) {
            filledFields++;
        }
        if (callbackData != null) {
            filledFields++;
            boolean isGreaterThanMax = callbackData.getBytes(StandardCharsets.UTF_8).length > MAX_CALLBACK_DATA_LENGTH;
            if (callbackData.isEmpty() || isGreaterThanMax) {
                throw new IllegalArgumentException("Field 'callbackData' must be between 1 and 64 bytes in UTF-8.");
            }
        }
        if (copyText != null) {
            filledFields++;
        }
        if (filledFields != 1) {
            throw new IllegalArgumentException("Exactly one of 'url', 'callbackData', 'copyText' must be present.");
        }
    }

    public static InlineKeyboardButton url(@Nonnull final String text, @Nonnull final String url) {
        return new InlineKeyboardButton(text, url, null, null);
    }

    public static InlineKeyboardButton callbackData(@Nonnull final String text, @Nonnull final String callbackData) {
        return new InlineKeyboardButton(text, null, callbackData, null);
    }

    public static InlineKeyboardButton copyText(@Nonnull final String text, @Nonnull final String copyText) {
        return new InlineKeyboardButton(text, null, null, new CopyTextButton(copyText));
    }
}
