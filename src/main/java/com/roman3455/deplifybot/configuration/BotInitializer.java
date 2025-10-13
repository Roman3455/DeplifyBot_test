package com.roman3455.deplifybot.configuration;

import com.roman3455.deplifybot.client.TelegramClient;
import com.roman3455.deplifybot.dto.telegram.api.request.SetWebhook;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

/**
 *
 */
@Component
public class BotInitializer {

    private static final Logger LOG = LoggerFactory.getLogger(BotInitializer.class);

    private static final int BYTES_SIZE = 32;
    private static final int FOURTH_TOKEN_CHAR = 4;

    private final TelegramClient telegramClient;
    private final String webhookUrl;
    private final String webhookPath;
    private String botApiToken;

    public BotInitializer(
            final TelegramClient telegramClient,
            @Value("${telegram.bot.webhook.url}") final String webhookUrl,
            @Value("${telegram.bot.webhook.path}") final String webhookPath
    ) {
        this.telegramClient = telegramClient;
        this.webhookUrl = webhookUrl;
        this.webhookPath = webhookPath;
    }

    /**
     *
     */
    @PostConstruct
    public void initBot() {
        generateBotApiToken();
        LOG.info("Generated Telegram bot API token [{}...]", botApiToken.substring(0, FOURTH_TOKEN_CHAR));
        try {
            setWebhook();
            LOG.info("Telegram bot secured webhook was SUCCESSFULLY set");
        } catch (Exception e) {
            LOG.error("FAILED to set Telegram secured webhook", e);
            throw new IllegalStateException("Cannot initialize Telegram secured webhook", e);
        }
    }

    private void generateBotApiToken() {
        byte[] randomBytes = new byte[BYTES_SIZE];
        new SecureRandom().nextBytes(randomBytes);
        botApiToken = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    private void setWebhook() {
        String webhookUrl = this.webhookUrl + webhookPath;
        SetWebhook webhook = SetWebhook.ofSecuredUrl(webhookUrl, botApiToken);
        telegramClient.setWebhook(webhook);
    }

    /**
     *
     */
    public String getBotApiToken() {
        return botApiToken;
    }
}
