package com.roman3455.deplifybot.exception.feign.telegram;

public class TelegramPayloadTooLargeException extends TelegramApiException {
    public TelegramPayloadTooLargeException(final String message) {
        super(message);
    }
}
