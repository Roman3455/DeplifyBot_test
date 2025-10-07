package com.roman3455.deplifybot.dto.telegram.api.ui;

import com.roman3455.deplifybot.dto.telegram.inbound.ChatAdministratorRights;
import jakarta.annotation.Nonnull;
import jakarta.validation.Valid;

import java.util.Objects;

public record KeyboardButtonRequestChat(
        @Nonnull Long requestId,
        boolean chatIsChannel,
        @Valid ChatAdministratorRights userAdministratorRights,
        @Valid ChatAdministratorRights botAdministratorRights,
        Boolean botIsMember,
        Boolean requestTitle
) {

    public KeyboardButtonRequestChat {
        Objects.requireNonNull(requestId, "Field 'request_id' cannot be null.");
    }
}
