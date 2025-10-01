package com.roman3455.deplifybot.client;

import com.roman3455.deplifybot.dto.telegram.AnswerCallbackQuery;
import com.roman3455.deplifybot.dto.telegram.EditMessageText;
import com.roman3455.deplifybot.dto.telegram.nested.Message;
import com.roman3455.deplifybot.dto.telegram.ResponseMessage;
import com.roman3455.deplifybot.dto.telegram.SendMessage;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "telegram")
public interface TelegramClient {

    @PostMapping(value = "/sendMessage", consumes = "application/json", produces = "application/json")
    ResponseMessage<Message> sendMessage(@RequestBody SendMessage body);

    @PostMapping(value = "/answerCallbackQuery", consumes = "application/json", produces = "application/json")
    ResponseMessage<Boolean> answerCallbackQuery(@RequestBody AnswerCallbackQuery body);

    @PostMapping(value = "/editMessageText", consumes = "application/json", produces = "application/json")
    ResponseMessage<Message> editMessageText(@RequestBody EditMessageText body);
}
