package com.roman3455.deplifybot.dto.telegram.inbound;

import com.roman3455.deplifybot.dto.telegram.api.response.Chat;

import java.time.Instant;

public record ChatMemberUpdated(
        Chat chat,
        User from,
        Instant date,
        ChatMember oldChatMember,
        ChatMember newChatMember
) {
}
