package com.roman3455.deplifybot.api;

import com.roman3455.deplifybot.configuration.BotInitializer;
import com.roman3455.deplifybot.dto.telegram.api.response.Update;
import com.roman3455.deplifybot.service.telegram.TelegramService;
import com.roman3455.deplifybot.util.telegram.TelegramApiTokenVerification;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("${telegram.bot.webhook.path}")
public final class TelegramWebhookController {

    private static final Logger LOG = LoggerFactory.getLogger(TelegramWebhookController.class);

    private final String botApiToken;
    private final TelegramService service;

    public TelegramWebhookController(final BotInitializer botInitializer, final TelegramService service) {
        this.botApiToken = botInitializer.getBotApiToken();
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Void> onUpdate(
            @RequestBody @Valid final Update update,
            @RequestHeader(value = "X-Telegram-Bot-Api-Secret-Token", required = false) final String receivedApiToken
    ) {
        TelegramApiTokenVerification.assertValid(update, botApiToken, receivedApiToken);
        CompletableFuture.runAsync(() -> service.processUpdate(update));
        return ResponseEntity.ok().build();
    }
}
