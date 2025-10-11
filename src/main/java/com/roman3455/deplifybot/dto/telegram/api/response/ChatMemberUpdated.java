package com.roman3455.deplifybot.dto.telegram.api.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;

public record ChatMemberUpdated(
        @NotNull @Valid Chat chat,
        @NotNull @Valid User from,
        @NotNull Instant date,
        @NotNull @Valid ChatMember oldChatMember,
        @NotNull @Valid ChatMember newChatMember
) {
}
