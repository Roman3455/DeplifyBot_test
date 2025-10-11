package com.roman3455.deplifybot.configuration;

import com.roman3455.deplifybot.client.TelegramClient;
import com.roman3455.deplifybot.dto.telegram.api.request.SetWebhook;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

/**
 */
@Component
public class BotInitializer {

    private static final int BYTES_SIZE = 32;

    private final TelegramClient telegramClient;
    private final String telegramWebhookUrl;
    private final String telegramWebhookPath;

    private String telegramApiToken;

    public BotInitializer(
            final TelegramClient telegramClient,
            @Value("${telegram.bot.webhook.url}") final String telegramWebhookUrl,
            @Value("${telegram.bot.webhook.path}") final String telegramWebhookPath
    ) {
        this.telegramClient = telegramClient;
        this.telegramWebhookUrl = telegramWebhookUrl;
        this.telegramWebhookPath = telegramWebhookPath;
    }

    /**
     */
    @PostConstruct
    public void initBot() {
        generateTelegramApiToken();
        setWebhook();
    }

    private void generateTelegramApiToken() {
        byte[] randomBytes = new byte[BYTES_SIZE];
        new SecureRandom().nextBytes(randomBytes);
        telegramApiToken = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    private void setWebhook() {
        String webhookUrl = telegramWebhookUrl + telegramWebhookPath;
        SetWebhook webhook = SetWebhook.ofSecuredUrl(webhookUrl, telegramApiToken);
        telegramClient.setWebhook(webhook);
    }

    /**
     */
    public String getTelegramApiToken() {
        return telegramApiToken;
    }
}
