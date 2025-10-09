package com.roman3455.deplifybot.exception.feign.telegram;

public abstract class TelegramApiException extends RuntimeException {
    public TelegramApiException(final String message) {
        super(message);
    }
}
