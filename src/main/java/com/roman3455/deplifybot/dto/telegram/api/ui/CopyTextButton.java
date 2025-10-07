package com.roman3455.deplifybot.dto.telegram.api.ui;

import jakarta.annotation.Nonnull;

import java.util.Objects;

public record CopyTextButton(
        @Nonnull String text
) {

    public static final int MAX_TEXT_LENGTH = 256;

    public CopyTextButton {
        Objects.requireNonNull(text, "Field 'text' cannot be null.");
        if (text.isEmpty() || text.length() > MAX_TEXT_LENGTH) {
            throw new IllegalArgumentException("Allowed 'text' length is 1 to 256 characters.");
        }
    }
}
