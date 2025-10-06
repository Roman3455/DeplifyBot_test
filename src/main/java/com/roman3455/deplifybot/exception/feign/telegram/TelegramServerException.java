package com.roman3455.deplifybot.exception.feign.telegram;

public class TelegramServerException extends TelegramApiException {
    public TelegramServerException(String message) {
        super(message);
    }
}
