package com.roman3455.deplifybot.exception.feign.telegram;

public abstract class TelegramApiException extends RuntimeException {
    public TelegramApiException(String message) {
        super(message);
    }
}
