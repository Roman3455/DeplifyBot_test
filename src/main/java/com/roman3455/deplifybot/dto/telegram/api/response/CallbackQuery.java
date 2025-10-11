package com.roman3455.deplifybot.dto.telegram.api.response;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public record CallbackQuery(
        @NotNull String Id,
        @NotNull @Valid User from,
        @Valid Message message,
        String inlineMessageId,
        String data
) {
}
