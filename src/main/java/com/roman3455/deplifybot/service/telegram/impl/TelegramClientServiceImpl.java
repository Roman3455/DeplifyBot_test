package com.roman3455.deplifybot.service.telegram.impl;

import com.roman3455.deplifybot.client.TelegramClient;
import com.roman3455.deplifybot.dto.telegram.api.enumiration.ParseModeType;
import com.roman3455.deplifybot.dto.telegram.api.request.AnswerCallbackQuery;
import com.roman3455.deplifybot.dto.telegram.api.request.EditMessageText;
import com.roman3455.deplifybot.dto.telegram.api.request.SendMessage;
import com.roman3455.deplifybot.dto.telegram.api.response.Message;
import com.roman3455.deplifybot.dto.telegram.api.response.ResponseBody;
import com.roman3455.deplifybot.dto.telegram.api.ui.ReplyMarkup;
import com.roman3455.deplifybot.service.telegram.TelegramClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TelegramClientServiceImpl implements TelegramClientService {

    private static final Logger LOG = LoggerFactory.getLogger(TelegramClientServiceImpl.class);

    private final TelegramClient telegramClient;

    public TelegramClientServiceImpl(final TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    /**
     */
    @Override
    public ResponseBody<Message> sendPrivateMessage(
            final Object chatId,
            final String text,
            final ParseModeType parseMode,
            final ReplyMarkup replyMarkup
    ) {
        try {
            var body = new SendMessage(chatId, null, text, parseMode, true, replyMarkup);
            return telegramClient.sendMessage(body);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     */
    @Override
    public ResponseBody<Boolean> answerCallbackQuery(final String callbackQueryId, final String text) {
        try {
            var body = new AnswerCallbackQuery(callbackQueryId, text);
            return telegramClient.answerCallbackQuery(body);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }
    }

    /**
     */
    @Override
    public ResponseBody<Message> editMessageText(
            final Long chatId,
            final Long messageId,
            final String text,
            final ParseModeType parseMode,
            final ReplyMarkup replyMarkup
    ) {
        try {
            var body = new EditMessageText(chatId, messageId, text, parseMode, replyMarkup);
            return telegramClient.editMessageText(body);
        } catch (Exception ex) {
            LOG.error(ex.getMessage(), ex);
            throw ex;
        }
    }
}
