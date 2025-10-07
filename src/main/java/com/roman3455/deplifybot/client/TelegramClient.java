package com.roman3455.deplifybot.client;

import com.roman3455.deplifybot.configuration.TelegramFeignConfig;
import com.roman3455.deplifybot.dto.telegram.api.request.AnswerCallbackQuery;
import com.roman3455.deplifybot.dto.telegram.api.request.EditMessageText;
import com.roman3455.deplifybot.dto.telegram.inbound.Message;
import com.roman3455.deplifybot.dto.telegram.inbound.TelegramResponseMessage;
import com.roman3455.deplifybot.dto.telegram.api.request.SendMessage;
import com.roman3455.deplifybot.dto.telegram.api.request.SetWebhook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "telegram", configuration = TelegramFeignConfig.class)
@RequestMapping(consumes = "application/json", produces = "application/json")
public interface TelegramClient {

    @PostMapping(value = "/setWebhook")
    TelegramResponseMessage<Boolean> setWebhook(@RequestBody SetWebhook body);

    @PostMapping(value = "/sendMessage")
    TelegramResponseMessage<Message> sendMessage(@RequestBody SendMessage body);

    @PostMapping(value = "/answerCallbackQuery")
    TelegramResponseMessage<Boolean> answerCallbackQuery(@RequestBody AnswerCallbackQuery body);

    @PostMapping(value = "/editMessageText")
    TelegramResponseMessage<Message> editMessageText(@RequestBody EditMessageText body);
}
