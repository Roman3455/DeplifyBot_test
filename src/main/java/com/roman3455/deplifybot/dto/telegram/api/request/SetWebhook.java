package com.roman3455.deplifybot.dto.telegram.api.request;

import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Objects;

public record SetWebhook(
        @Nonnull String url,
        Integer maxConnections,
        List<String> allowedUpdates,
        Boolean dropPendingUpdates,
        String secretToken
) {

    public SetWebhook {
        Objects.requireNonNull(url, "Field 'url' cannot be null");
    }

    public static SetWebhook ofSecuredUrl(@Nonnull final String url, final String secretToken) {
        return new SetWebhook(url, null, null, null, secretToken);
    }
}
