package com.roman3455.deplifybot.dto.telegram.api.response;

import jakarta.validation.constraints.NotNull;

public record User(
        @NotNull Long id,
        boolean isBot,
        String firstName,
        String username,
        String languageCode
) {
}
