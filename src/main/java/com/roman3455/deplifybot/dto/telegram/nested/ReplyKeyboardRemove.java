package com.roman3455.deplifybot.dto.telegram.nested;

import com.roman3455.deplifybot.dto.telegram.ReplyMarkup;

public record ReplyKeyboardRemove(boolean removeKeyboard, Boolean selective) implements ReplyMarkup {

    public ReplyKeyboardRemove {
        if (!removeKeyboard) {
            throw new IllegalArgumentException("Field 'removeKeyboard' must be true.");
        }
    }

    public static ReplyKeyboardRemove allUsers() {
        return new ReplyKeyboardRemove(true, null);
    }

    public static ReplyKeyboardRemove selectiveUsers() {
        return new ReplyKeyboardRemove(true, true);
    }
}
