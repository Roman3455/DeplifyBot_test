package com.roman3455.deplifybot.dto.telegram;

import com.roman3455.deplifybot.dto.telegram.nested.CallbackQuery;
import com.roman3455.deplifybot.dto.telegram.nested.ChatMemberUpdated;
import com.roman3455.deplifybot.dto.telegram.nested.Message;

public record Update(
        Long updateId,
        Message message,
        CallbackQuery callbackQuery,
        ChatMemberUpdated myChatMember
) {

    public boolean hasMessage(final Update update) {
        return update.message != null;
    }

    public boolean hasCallbackQuery(final Update update) {
        return update.callbackQuery != null;
    }
}
