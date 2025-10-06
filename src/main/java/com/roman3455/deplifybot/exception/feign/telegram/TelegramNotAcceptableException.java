package com.roman3455.deplifybot.exception.feign.telegram;

public class TelegramNotAcceptableException extends TelegramApiException {
    public TelegramNotAcceptableException(String message) {
        super(message);
    }
}
