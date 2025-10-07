package com.roman3455.deplifybot.dto.telegram.api.ui;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;

import java.util.Objects;

public record KeyboardButton(
        @Nonnull String text,
        @Valid KeyboardButtonRequestChat requestChat
) {

    public KeyboardButton {
        Objects.requireNonNull(text, "Field 'text' cannot be null.");
    }

    public static KeyboardButton text(@Nonnull final String text) {
        return new KeyboardButton(text, null);
    }
}
