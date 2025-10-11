package com.roman3455.deplifybot.dto.telegram.api.ui;

public record ReplyKeyboardRemove(
        boolean removeKeyboard,
        Boolean selective
) implements ReplyMarkup {

    public ReplyKeyboardRemove {
        if (!removeKeyboard) {
            throw new IllegalArgumentException("Field 'removeKeyboard' must be true.");
        }
    }

    public static ReplyKeyboardRemove forAllUsers() {
        return new ReplyKeyboardRemove(true, null);
    }
}
