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

    ResponseBody<Boolean> answerCallbackQuery(String callbackQueryId, String text);

    default ResponseBody<Boolean> emptyAnswerCallbackQuery(String callbackQueryId) {
        return answerCallbackQuery(callbackQueryId, null);
    }

    ResponseBody<Message> editMessageText(
            Long chatId,
            Long messageId,
            String text,
            ParseModeType parseMode,
            ReplyMarkup replyMarkup
    );

    default ResponseBody<Message> editMarkdownMessageText(
            Long chatId,
            Long messageId,
            String text,
            ReplyMarkup replyMarkup
    ) {
        return editMessageText(chatId, messageId, text, ParseModeType.MARKDOWN_V2, replyMarkup);
    }

    default ResponseBody<Message> editHtmlMessageText(
            Long chatId,
            Long messageId,
            String text,
            ReplyMarkup replyMarkup
    ) {
        return editMessageText(chatId, messageId, text, ParseModeType.HTML, replyMarkup);
    }

    default ResponseBody<Message> editPlainMessageText(
            Long chatId,
            Long messageId,
            String text,
            ReplyMarkup replyMarkup
    ) {
        return editMessageText(chatId, messageId, text, ParseModeType.PLAIN, replyMarkup);
    }
}
