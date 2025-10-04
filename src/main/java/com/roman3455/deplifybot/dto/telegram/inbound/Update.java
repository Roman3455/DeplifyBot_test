package com.roman3455.deplifybot.dto.telegram.inbound;

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
