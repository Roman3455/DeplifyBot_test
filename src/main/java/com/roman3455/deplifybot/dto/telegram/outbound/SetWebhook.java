package com.roman3455.deplifybot.dto.telegram.outbound;

import jakarta.annotation.Nonnull;

import java.util.Objects;

public record SetWebhook(
        @Nonnull String url,
        Integer maxConnections,
        String[] allowedUpdates,
        Boolean dropPendingUpdates,
        String secretToken
) {

    public SetWebhook {
        Objects.requireNonNull(url, "Field 'url' cannot be null");
    }
}
