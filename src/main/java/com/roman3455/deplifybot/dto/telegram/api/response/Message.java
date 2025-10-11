package com.roman3455.deplifybot.dto.telegram.api.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record Message(
        @NotNull Long messageId,
        Long messageThreadId,
        @Valid User from,
        @NotNull Instant date,
        @NotNull Chat chat,
        String text,
        Long migrateToChatId,
        Long migrateFromChatId,
        @Valid ChatShared chatShared
) {
    public boolean hasText() {
        return this.text != null && !this.text.isBlank();
    }
}
