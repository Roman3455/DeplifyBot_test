package com.roman3455.deplifybot.exception.feign.telegram;

public class TelegramUnauthorizedException extends TelegramApiException {
    public TelegramUnauthorizedException(String message) {
        super(message);
    }
}
