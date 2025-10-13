package com.roman3455.deplifybot.service.telegram.impl;

import com.roman3455.deplifybot.client.TelegramClient;
import com.roman3455.deplifybot.dto.telegram.api.enumiration.ParseModeType;
import com.roman3455.deplifybot.dto.telegram.api.request.SendMessage;
import com.roman3455.deplifybot.dto.telegram.api.response.Message;
import com.roman3455.deplifybot.dto.telegram.api.response.ResponseBody;
import com.roman3455.deplifybot.dto.telegram.api.ui.ReplyMarkup;
import com.roman3455.deplifybot.exception.feign.telegram.TelegramApiException;
import com.roman3455.deplifybot.service.telegram.TelegramClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TelegramClientServiceImpl implements TelegramClientService {

    private static final Logger LOG = LoggerFactory.getLogger(TelegramClientServiceImpl.class);

    private final TelegramClient telegramClient;

    public TelegramClientServiceImpl(TelegramClient telegramClient) {
        this.telegramClient = telegramClient;
    }

    @Override
    public ResponseBody<Message> sendPrivateMessage(
            final Object chatId,
            final String text,
            final ParseModeType parseMode,
            final ReplyMarkup replyMarkup
    ) {
        var message = new SendMessage(chatId, null, text, parseMode, true, replyMarkup);
        try {
            var response = telegramClient.sendMessage(message);
            LOG.info("Sent message to telegram");
            return response;
        } catch (TelegramApiException e) {
            LOG.error(e.getMessage(), e);
            throw e;
        }
    }
}
// todo поставить фильтр по private чатам
