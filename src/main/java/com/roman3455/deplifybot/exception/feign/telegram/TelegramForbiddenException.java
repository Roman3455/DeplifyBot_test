package com.roman3455.deplifybot.exception.feign.telegram;

public class TelegramForbiddenException extends TelegramApiException {
    public TelegramForbiddenException(String message) {
        super(message);
    }
}
