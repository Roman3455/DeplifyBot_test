package com.roman3455.deplifybot.dto.telegram.api.response;

import com.roman3455.deplifybot.dto.telegram.api.enumiration.ChatType;
import jakarta.validation.constraints.NotNull;

public record Chat(
        @NotNull Long id,
        @NotNull ChatType type,
        String title,
        String username,
        String firstName,
        Boolean isForum
) {
}
