package com.roman3455.deplifybot.dto.telegram.inbound;

public record ChatMember(
        String status,
        User user
) {
}
