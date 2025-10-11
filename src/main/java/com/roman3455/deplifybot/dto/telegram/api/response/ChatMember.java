package com.roman3455.deplifybot.dto.telegram.api.response;

import com.roman3455.deplifybot.dto.telegram.api.enumiration.ChatMemberStatusType;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record ChatMember(
        @NotNull ChatMemberStatusType status,
        @NotNull @Valid User user
) {
}
