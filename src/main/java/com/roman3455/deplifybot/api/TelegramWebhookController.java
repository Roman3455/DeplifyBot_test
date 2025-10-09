package com.roman3455.deplifybot.api;

import com.roman3455.deplifybot.configuration.BotInitializer;
import com.roman3455.deplifybot.dto.telegram.inbound.Update;
import com.roman3455.deplifybot.service.telegram.UpdateDispatcher;
import com.roman3455.deplifybot.util.telegram.TelegramApiTokenVerification;
import jakarta.validation.Valid;
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

    private final BotInitializer botInitializer;
    private final UpdateDispatcher dispatcher;

    public TelegramWebhookController(final BotInitializer botInitializer, final UpdateDispatcher dispatcher) {
        this.botInitializer = botInitializer;
        this.dispatcher = dispatcher;
    }

    @PostMapping
    public ResponseEntity<Void> onUpdate(
            @RequestBody @Valid final Update update,
            @RequestHeader(value = "X-Telegram-Bot-Api-Secret-Token", required = false) final String apiSecretToken
    ) {
        TelegramApiTokenVerification.assertValid(botInitializer.getTelegramApiToken(), apiSecretToken);
        CompletableFuture.runAsync(() -> dispatcher.dispatch(update));
        return ResponseEntity.ok().build();
    }
}
