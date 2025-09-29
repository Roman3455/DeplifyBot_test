package com.roman3455.deplifybot.dto.telegram.nested;

import com.roman3455.deplifybot.dto.telegram.ReplyMarkup;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Objects;

public record ReplyKeyboardMarkup(
        @Nonnull List<List<KeyboardButton>> keyboard,
        Boolean isPersistent,
        Boolean resizeKeyboard,
        Boolean oneTimeKeyboard,
        String inputFieldPlaceholder,
        Boolean selective
) implements ReplyMarkup {

    public static final int MAX_PLACEHOLDER_LENGTH = 64;

    public ReplyKeyboardMarkup {
        Objects.requireNonNull(keyboard, "Field 'keyboard' must not be null.");
        if (inputFieldPlaceholder.isEmpty() || inputFieldPlaceholder.length() > MAX_PLACEHOLDER_LENGTH) {
            throw new IllegalArgumentException("Allowed 'inputFieldPlaceholder' length is 1 to 64 characters.");
        }
    }

    public static ReplyKeyboardMarkup standardMarkup(
            final List<List<KeyboardButton>> keyboard,
            final Boolean resizeKeyboard,
            final Boolean oneTimeKeyboard
    ) {
        return new ReplyKeyboardMarkup(
                keyboard, null, resizeKeyboard, oneTimeKeyboard, null, null
        );
    }
}
