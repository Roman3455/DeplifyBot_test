package com.roman3455.deplifybot.service.telegram.callback.impl;

import com.roman3455.deplifybot.dto.telegram.api.response.Update;
import com.roman3455.deplifybot.dto.telegram.api.ui.ReplyMarkup;
import com.roman3455.deplifybot.dto.telegram.factory.InlineKeyboardFactory;
import com.roman3455.deplifybot.dto.telegram.factory.InlineKeyboardType;
import com.roman3455.deplifybot.service.telegram.TelegramClientService;
import com.roman3455.deplifybot.service.telegram.callback.Callback;
import com.roman3455.deplifybot.service.telegram.callback.CallbackType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public final class StartMenuCallback implements Callback {

    private static final Logger LOG = LoggerFactory.getLogger(StartMenuCallback.class);

    private final TelegramClientService clientService;
    private final MessageSource messageSource;

    public StartMenuCallback(final TelegramClientService clientService, final MessageSource messageSource) {
        this.clientService = clientService;
        this.messageSource = messageSource;
    }

    @Override
    public void apply(final Update update) {
        String callbackQueryId = update.callbackQuery().Id();
        var answerResponse = clientService.emptyAnswerCallbackQuery(callbackQueryId);
        if (!answerResponse.ok()) {
            LOG.warn("Update [{}]: Failed to send answer CallbackQuery. Error code {}, {}",
                    update.updateId(), answerResponse.errorCode(), answerResponse.description()
            );
        } else {
            LOG.info("Update [{}]: Successfully sent answer CallbackQuery.", update.updateId());
        }

        long callbackQueryChatId = update.callbackQuery().message().chat().id();
        long callbackQueryMessageId = update.callbackQuery().message().messageId();
        String sourceText = messageSource.getMessage("start.menu.message", null, null);
        ReplyMarkup inlineKeyboardMarkup = InlineKeyboardFactory.create(InlineKeyboardType.MENU_KEYBOARD);
        var editResponse = clientService.editPlainMessageText(
                callbackQueryChatId,
                callbackQueryMessageId,
                sourceText,
                inlineKeyboardMarkup
        );
        if (!editResponse.ok()) {
            LOG.warn("Update [{}]: Failed to process #start_menu. Error code {}, {}",
                    update.updateId(), editResponse.errorCode(), editResponse.description()
            );
        } else {
            LOG.info("Update [{}]: Successfully processed #start_menu.", update.updateId());
        }
    }

    @Override
    public CallbackType getType() {
        return CallbackType.START_MENU;
    }
}
