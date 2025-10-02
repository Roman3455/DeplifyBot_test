package com.roman3455.deplifybot.util;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public final class TelegramApiTokenVerification {

    private static final String HEADER = "X-Telegram-Bot-Api-Secret-Token";

    public static void assertValid(final String webhookSecret, final String receivedSecret)
            throws ResponseStatusException {
        if (receivedSecret == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing " + HEADER);
        }

        byte[] expectedSecret = webhookSecret.getBytes(StandardCharsets.UTF_8);
        byte[] actualSecret = receivedSecret.getBytes(StandardCharsets.UTF_8);
        if (!MessageDigest.isEqual(expectedSecret, actualSecret)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid " + HEADER);
        }
    }
}
