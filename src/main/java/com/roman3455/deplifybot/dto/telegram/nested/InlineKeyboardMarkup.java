package com.roman3455.deplifybot.dto.telegram.nested;

import com.roman3455.deplifybot.dto.telegram.ReplyMarkup;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Objects;

public record InlineKeyboardMarkup(@Nonnull List<List<InlineKeyboardButton>> inlineKeyboard) implements ReplyMarkup {

    public InlineKeyboardMarkup {
        Objects.requireNonNull(inlineKeyboard, "Field 'inlineKeyboard' must not be null.");
    }
}
