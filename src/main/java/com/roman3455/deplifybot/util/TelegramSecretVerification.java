package com.roman3455.deplifybot.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

@Component
public final class TelegramSecretVerification {

    private static final String HEADER = "X-Telegram-Bot-Api-Secret-Token";
    private final byte[] expectedSecret;

    public TelegramSecretVerification(@Value("${telegram.bot.webhook.secret}") final String expectedSecret) {
        this.expectedSecret = expectedSecret.getBytes(StandardCharsets.UTF_8);
    }

    public void assertValid(final String received) throws ResponseStatusException {
        if (received == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing " + HEADER);
        }
        byte[] receivedSecret = received.getBytes(StandardCharsets.UTF_8);
        if (!MessageDigest.isEqual(expectedSecret, receivedSecret)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid " + HEADER);
        }
    }
}
