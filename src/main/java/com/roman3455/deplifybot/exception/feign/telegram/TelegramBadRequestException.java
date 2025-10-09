package com.roman3455.deplifybot.exception.feign.telegram;

public class TelegramBadRequestException extends  TelegramApiException {
    public TelegramBadRequestException(final String message) {
        super(message);
    }
}
