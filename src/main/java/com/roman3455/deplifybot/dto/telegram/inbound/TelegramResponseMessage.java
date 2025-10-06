package com.roman3455.deplifybot.dto.telegram.inbound;

public record TelegramResponseMessage<T>(
        boolean ok,
        T result,
        Integer errorCode,
        String description,
        ResponseParameters parameters
) {
    public record ResponseParameters(Long migrateToChatId, Integer retryAfter) {
    }
}
