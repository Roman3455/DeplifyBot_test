package com.roman3455.deplifybot.dto.telegram.api.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record Update(
        @NotNull Long updateId,
        @Valid Message message,
        @Valid CallbackQuery callbackQuery,
        @Valid ChatMemberUpdated myChatMember
) {

    public boolean hasMessage() {
        return this.message != null;
    }

    public boolean hasCallbackQuery() {
        return this.callbackQuery != null;
    }
}
