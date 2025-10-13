package com.roman3455.deplifybot.service.telegram;

import com.roman3455.deplifybot.dto.telegram.api.enumiration.ParseModeType;
import com.roman3455.deplifybot.dto.telegram.api.response.Message;
import com.roman3455.deplifybot.dto.telegram.api.response.ResponseBody;
import com.roman3455.deplifybot.dto.telegram.api.ui.ReplyMarkup;

public interface TelegramClientService {

    ResponseBody<Message> sendPrivateMessage(
            Object chatId,
            String text,
            ParseModeType parseMode,
            ReplyMarkup replyMarkup
    );

    default ResponseBody<Message> sendPrivateMarkdownMessage(Object chatId, String text, ReplyMarkup replyMarkup) {
        return sendPrivateMessage(chatId, text, ParseModeType.MARKDOWN_V2, replyMarkup);
    }

    default ResponseBody<Message> sendPrivateHtmlMessage(Object chatId, String text, ReplyMarkup replyMarkup) {
        return sendPrivateMessage(chatId, text, ParseModeType.HTML, replyMarkup);
    }

    default ResponseBody<Message> sendPrivatePlainMessage(Object chatId, String text, ReplyMarkup replyMarkup) {
        return sendPrivateMessage(chatId, text, ParseModeType.PLAIN, replyMarkup);
    }
}
