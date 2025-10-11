package com.roman3455.deplifybot.client;

import com.roman3455.deplifybot.configuration.TelegramFeignConfig;
import com.roman3455.deplifybot.dto.telegram.api.request.AnswerCallbackQuery;
import com.roman3455.deplifybot.dto.telegram.api.request.EditMessageText;
import com.roman3455.deplifybot.dto.telegram.api.response.Message;
import com.roman3455.deplifybot.dto.telegram.api.response.ResponseBody;
import com.roman3455.deplifybot.dto.telegram.api.request.SendMessage;
import com.roman3455.deplifybot.dto.telegram.api.request.SetWebhook;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "telegram", configuration = TelegramFeignConfig.class)
public interface TelegramClient {

    @PostMapping(value = "/setWebhook", consumes = "application/json", produces = "application/json")
    ResponseBody<Boolean> setWebhook(@RequestBody SetWebhook body);

    @PostMapping(value = "/sendMessage", consumes = "application/json", produces = "application/json")
    ResponseBody<Message> sendMessage(@RequestBody SendMessage body);

    @PostMapping(value = "/answerCallbackQuery", consumes = "application/json", produces = "application/json")
    ResponseBody<Boolean> answerCallbackQuery(@RequestBody AnswerCallbackQuery body);

    @PostMapping(value = "/editMessageText", consumes = "application/json", produces = "application/json")
    ResponseBody<Message> editMessageText(@RequestBody EditMessageText body);
}
