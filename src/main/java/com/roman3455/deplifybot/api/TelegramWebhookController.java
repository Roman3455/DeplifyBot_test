package com.roman3455.deplifybot.api;

import com.roman3455.deplifybot.dto.telegram.Update;
import com.roman3455.deplifybot.service.telegram.UpdateRouter;
import com.roman3455.deplifybot.util.TelegramSecretVerification;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${telegram.bot.webhook.url}")
public class TelegramWebhookController {

    TelegramSecretVerification verification;
    UpdateRouter dispatcher;

    public TelegramWebhookController(TelegramSecretVerification verification, UpdateRouter dispatcher) {
        this.verification = verification;
        this.dispatcher = dispatcher;
    }

    @PostMapping
    public ResponseEntity<Void> onUpdate(
            @RequestBody @Valid final Update update,
            @RequestHeader(value = "X-Telegram-Bot-Api-Secret-Token") String secret
    ) {
        verification.assertValid(secret);
        dispatcher.route(update);
        return ResponseEntity.ok().build();
    }
}
