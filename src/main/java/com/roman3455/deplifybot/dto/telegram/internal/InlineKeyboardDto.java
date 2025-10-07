package com.roman3455.deplifybot.dto.telegram.internal;

import com.roman3455.deplifybot.dto.telegram.api.ui.InlineKeyboardButton;
import com.roman3455.deplifybot.dto.telegram.api.ui.InlineKeyboardMarkup;
import com.roman3455.deplifybot.dto.telegram.api.ui.ReplyMarkup;
import com.roman3455.deplifybot.service.telegram.BotActionType;

import java.util.Arrays;
import java.util.List;

public class InlineKeyboardDto {

    public static List<InlineKeyboardButton> setEnumCallbackButtonsRow(BotActionType... botActionTypes) {
        return Arrays.stream(botActionTypes)
                .map(action -> InlineKeyboardButton.callbackData(action.getDescription(), action.getName()))
                .toList();
    }

    @SafeVarargs
    public static ReplyMarkup setEnumCallbackKeyboard(List<InlineKeyboardButton>... inlineKeyboardButtons) {
        return new InlineKeyboardMarkup(Arrays.stream(inlineKeyboardButtons).toList());
    }
}
