package com.roman3455.deplifybot.dto.telegram.nested;

import jakarta.annotation.Nonnull;

import java.util.Objects;

public record KeyboardButtonRequestChat(
        @Nonnull Long requestId,
        boolean chatIsChannel,
        ChatAdministratorRights userAdministratorRights,
        ChatAdministratorRights botAdministratorRights,
        Boolean botIsMember,
        Boolean requestTitle
) {

    public KeyboardButtonRequestChat {
        Objects.requireNonNull(requestId, "Field 'request_id' must not be null.");
    }
}
