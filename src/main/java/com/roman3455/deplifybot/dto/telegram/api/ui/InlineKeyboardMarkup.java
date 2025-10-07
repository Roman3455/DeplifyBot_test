package com.roman3455.deplifybot.dto.telegram.api.ui;

import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public record InlineKeyboardMarkup(
        @Nonnull List<List<@Valid InlineKeyboardButton>> inlineKeyboard
) implements ReplyMarkup {

    public InlineKeyboardMarkup {
        Objects.requireNonNull(inlineKeyboard, "Field 'inlineKeyboard' cannot be null.");
    }

    @SafeVarargs
    public static InlineKeyboardMarkup ofRows(@Nonnull final List<InlineKeyboardButton>... inlineKeyboards) {
        List<List<InlineKeyboardButton>> rows = Arrays.stream(inlineKeyboards)
                .map(List::copyOf)
                .toList();
        return new InlineKeyboardMarkup(rows);
    }
}
