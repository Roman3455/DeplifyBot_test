package com.roman3455.deplifybot.dto.telegram;

public record ResponseMessage<T>(
        boolean ok,
        T result,
        Integer errorCode,
        String description,
        ResponseParameters parameters
) {
    public record ResponseParameters(Long migrateToChatId, Integer retryAfter) {
    }
}
