package com.roman3455.deplifybot.client;

import com.roman3455.deplifybot.configuration.TelegramFeignConfig;
import com.roman3455.deplifybot.dto.telegram.outbound.AnswerCallbackQuery;
import com.roman3455.deplifybot.dto.telegram.outbound.EditMessageText;
import com.roman3455.deplifybot.dto.telegram.inbound.Message;
import com.roman3455.deplifybot.dto.telegram.inbound.TelegramResponseMessage;
import com.roman3455.deplifybot.dto.telegram.outbound.SendMessage;
import com.roman3455.deplifybot.dto.telegram.outbound.SetWebhook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "telegram", configuration = TelegramFeignConfig.class)
public interface TelegramClient {

    @PostMapping(value = "/setWebhook", consumes = "application/json", produces = "application/json")
    TelegramResponseMessage<Boolean> setWebhook(@RequestBody SetWebhook body);

    @PostMapping(value = "/sendMessage", consumes = "application/json", produces = "application/json")
    TelegramResponseMessage<Message> sendMessage(@RequestBody SendMessage body);

    @PostMapping(value = "/answerCallbackQuery", consumes = "application/json", produces = "application/json")
    TelegramResponseMessage<Boolean> answerCallbackQuery(@RequestBody AnswerCallbackQuery body);

    @PostMapping(value = "/editMessageText", consumes = "application/json", produces = "application/json")
    TelegramResponseMessage<Message> editMessageText(@RequestBody EditMessageText body);
}
