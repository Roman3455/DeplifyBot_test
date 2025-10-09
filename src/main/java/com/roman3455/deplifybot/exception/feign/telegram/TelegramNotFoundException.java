package com.roman3455.deplifybot.exception.feign.telegram;

public class TelegramNotFoundException extends TelegramApiException {
    public TelegramNotFoundException(final String message) {
        super(message);
    }
}
