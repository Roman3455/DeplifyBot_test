package com.roman3455.deplifybot.dto.telegram.api.response;

import jakarta.validation.constraints.NotNull;

public record ChatShared(
        @NotNull Long requestId,
        @NotNull Long chatId,
        String title,
        String username
) {
}
