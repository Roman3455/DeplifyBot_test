package com.roman3455.deplifybot.dto.telegram.api.ui;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record ReplyKeyboardMarkup(
        @Nonnull List<List<@Valid KeyboardButton>> keyboard,
        Boolean isPersistent,
        Boolean resizeKeyboard,
        Boolean oneTimeKeyboard,
        String inputFieldPlaceholder,
        Boolean selective
) implements ReplyMarkup {

    public static final int MAX_PLACEHOLDER_LENGTH = 64;

    public ReplyKeyboardMarkup {
        Objects.requireNonNull(keyboard, "Field 'keyboard' cannot be null.");
        if (inputFieldPlaceholder != null && (inputFieldPlaceholder.isEmpty()
                || inputFieldPlaceholder.length() > MAX_PLACEHOLDER_LENGTH)) {
            throw new IllegalArgumentException("Allowed 'inputFieldPlaceholder' length is 1 to 64 characters.");
        }
    }

    @SafeVarargs
    public static ReplyKeyboardMarkup ofResizedOneTimeKeyboard(@Nonnull final List<KeyboardButton>... keyboards) {
        return ofRows(
                null,
                true,
                true,
                null,
                null,
                keyboards
        );
    }

    @SafeVarargs
    public static ReplyKeyboardMarkup ofResizedPersistentKeyboard(@Nonnull final List<KeyboardButton>... keyboards) {
        return ofRows(
                true,
                true,
                false,
                null,
                null,
                keyboards
        );
    }

    @SafeVarargs
    public static ReplyKeyboardMarkup ofRows(
            final Boolean isPersistent,
            final Boolean resizeKeyboard,
            final Boolean oneTimeKeyboard,
            final String inputFieldPlaceholder,
            final Boolean selective,
            @Nonnull final List<KeyboardButton>... keyboards
    ) {
        List<List<KeyboardButton>> rows = Arrays.stream(keyboards)
                .map(List::copyOf)
                .toList();
        return new ReplyKeyboardMarkup(
                rows,
                isPersistent,
                resizeKeyboard,
                oneTimeKeyboard,
                inputFieldPlaceholder,
                selective
        );
    }
}
