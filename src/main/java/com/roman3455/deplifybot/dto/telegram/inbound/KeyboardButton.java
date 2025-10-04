package com.roman3455.deplifybot.dto.telegram.inbound;

import jakarta.annotation.Nonnull;

import java.util.Objects;

public record KeyboardButton(
        @Nonnull String text,
        KeyboardButtonRequestChat requestChat
) {

    public KeyboardButton {
        Objects.requireNonNull(text, "Field 'text' must not be null.");
    }

    public static KeyboardButton text(@Nonnull final String text) {
        return new KeyboardButton(text, null);
    }
}
