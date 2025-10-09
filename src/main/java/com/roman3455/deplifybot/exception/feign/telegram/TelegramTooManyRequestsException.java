package com.roman3455.deplifybot.exception.feign.telegram;

public class TelegramTooManyRequestsException extends TelegramApiException {
    private final long retryAfter;

    public TelegramTooManyRequestsException(final String message, final long retryAfter) {
        super(message);
        this.retryAfter = retryAfter;
    }

    /**
     */
    public long getRetryAfter() {
        return retryAfter;
    }
}
