package com.roman3455.deplifybot.dto.telegram.nested;

import java.time.Instant;

public record Message(
        Long messageId,
        Long messageThreadId,
        User from,
        Instant date,
        Chat chat,
        String text,
        Long migrateToChatId,
        Long migrateFromChatId,
        ChatShared chatShared
) {
    public boolean hasText(final Message message) {
        return message.text != null && !message.text.isBlank();
    }
}
