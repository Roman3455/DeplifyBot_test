package com.roman3455.deplifybot.configuration;

import com.roman3455.deplifybot.client.TelegramClient;
import com.roman3455.deplifybot.dto.telegram.outbound.SetWebhook;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;

@Component
public class BotInitializer {

    private final TelegramClient telegramClient;
    private final String telegramWebhookUrl;

    private String telegramApiToken;

    public BotInitializer(
            TelegramClient telegramClient,
            @Value("${telegram.bot.webhook.url}") String telegramWebhookUrl
    ) {
        this.telegramClient = telegramClient;
        this.telegramWebhookUrl = telegramWebhookUrl;
    }

    @PostConstruct
    public void initBot() {
        generateTelegramApiToken();
        setWebhook(telegramWebhookUrl);
        System.out.println(this.telegramApiToken);
    }

    private void generateTelegramApiToken() {
        byte[] randomBytes = new byte[32];
        new SecureRandom().nextBytes(randomBytes);
        telegramApiToken = Base64.getUrlEncoder().withoutPadding().encodeToString(randomBytes);
    }

    private void setWebhook(String webhookUrl) {
        var webhook = new SetWebhook(webhookUrl, null, null, null, telegramApiToken);
        telegramClient.setWebhook(webhook);
    }

    public String getTelegramApiToken() {
        return telegramApiToken;
    }
}
