package com.roman3455.deplifybot.service.telegram.callback;

import com.roman3455.deplifybot.client.TelegramClient;
import com.roman3455.deplifybot.dto.telegram.api.request.AnswerCallbackQuery;
import com.roman3455.deplifybot.dto.telegram.api.request.EditMessageText;
import com.roman3455.deplifybot.dto.telegram.api.response.Update;
import com.roman3455.deplifybot.dto.telegram.factory.InlineKeyboardFactory;
import com.roman3455.deplifybot.dto.telegram.factory.InlineKeyboardType;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public final class StartMenuCallback implements Callback {

    private final TelegramClient client;
    private final MessageSource messageSource;

    public StartMenuCallback(final TelegramClient client, final MessageSource messageSource) {
        this.client = client;
        this.messageSource = messageSource;
    }

    @Override
    public void apply(final Update update) {
        String callbackQueryId = update.callbackQuery().Id();
        var answerCallbackQuery = AnswerCallbackQuery.empty(callbackQueryId);
        client.answerCallbackQuery(answerCallbackQuery);

        var editMessageText = EditMessageText.ofPlain(
                update.callbackQuery().message().chat().id(),
                update.callbackQuery().message().messageId(),
                messageSource.getMessage("start.menu.message", null, null),
                InlineKeyboardFactory.create(InlineKeyboardType.MENU_KEYBOARD)
        );
        client.editMessageText(editMessageText);
    }

    @Override
    public CallbackType getType() {
        return CallbackType.START_MENU;
    }
}
