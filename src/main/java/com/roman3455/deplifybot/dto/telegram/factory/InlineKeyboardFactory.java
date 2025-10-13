package com.roman3455.deplifybot.dto.telegram.factory;

import com.roman3455.deplifybot.dto.telegram.api.ui.InlineKeyboardButton;
import com.roman3455.deplifybot.dto.telegram.api.ui.InlineKeyboardMarkup;
import com.roman3455.deplifybot.dto.telegram.api.ui.ReplyMarkup;
import com.roman3455.deplifybot.service.telegram.callback.CallbackType;

import java.util.List;

public final class InlineKeyboardFactory {

    private InlineKeyboardFactory() {
    }

    public static ReplyMarkup create(final InlineKeyboardType keyboardType) {
        return switch (keyboardType) {
            case START_MENU_KEYBOARD -> startMenuKeyboard();
            case MENU_KEYBOARD -> menuKeyboard();
        };
    }

    private static ReplyMarkup startMenuKeyboard() {
        var buttonRow1 = List.of(createButton(CallbackType.START_MENU));
        return InlineKeyboardMarkup.ofRows(buttonRow1);
    }

    private static ReplyMarkup menuKeyboard() {
        var buttonRow1 = List.of(createButton(CallbackType.SUBSCRIBE));
        var buttonRow2 = List.of(createButton(CallbackType.MANAGE));
        var buttonRow3 = List.of(createButton(CallbackType.SUBSCRIPTIONS));
        var buttonRow4 = List.of(
                createButton(CallbackType.INFO),
                createButton(CallbackType.FEEDBACK)
        );
        return InlineKeyboardMarkup.ofRows(buttonRow1, buttonRow2, buttonRow3, buttonRow4);
    }

    private static InlineKeyboardButton createButton(final CallbackType callbackType) {
        return InlineKeyboardButton.ofCallbackData(callbackType.getDescription(), callbackType.getName());
    }
}
