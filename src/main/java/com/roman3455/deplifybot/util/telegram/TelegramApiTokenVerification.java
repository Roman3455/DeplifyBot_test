package com.roman3455.deplifybot.util.telegram;

import com.roman3455.deplifybot.dto.telegram.api.response.Update;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public final class TelegramApiTokenVerification {

    private static final Logger LOG = LoggerFactory.getLogger(TelegramApiTokenVerification.class);
    private static final String HEADER = "X-Telegram-Bot-Api-Secret-Token";

    private TelegramApiTokenVerification() {
    }

    public static void assertValid(final Update update, final String botApiToken, final String receivedApiToken)
            throws ResponseStatusException {
        if (receivedApiToken == null) {
            LOG.warn("Update [{}]: received null Telegram API token", update.updateId());
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Missing " + HEADER);
        }

        byte[] expectedToken = botApiToken.getBytes(StandardCharsets.UTF_8);
        byte[] actualToken = receivedApiToken.getBytes(StandardCharsets.UTF_8);
        if (!MessageDigest.isEqual(expectedToken, actualToken)) {
            LOG.warn("Update [{}]: received invalid Telegram API token", update.updateId());
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid " + HEADER);
        }
    }
}
