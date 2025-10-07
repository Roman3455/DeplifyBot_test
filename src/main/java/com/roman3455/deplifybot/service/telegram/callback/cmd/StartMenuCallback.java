package com.roman3455.deplifybot.service.telegram.callback.cmd;

import com.roman3455.deplifybot.client.TelegramClient;
import com.roman3455.deplifybot.dto.telegram.api.request.AnswerCallbackQuery;
import com.roman3455.deplifybot.dto.telegram.api.request.EditMessageText;
import com.roman3455.deplifybot.dto.telegram.api.ui.ReplyMarkup;
import com.roman3455.deplifybot.dto.telegram.inbound.Update;
import com.roman3455.deplifybot.dto.telegram.api.ui.InlineKeyboardButton;
import com.roman3455.deplifybot.dto.telegram.api.ui.InlineKeyboardMarkup;
import com.roman3455.deplifybot.service.telegram.callback.Callback;
import com.roman3455.deplifybot.service.telegram.callback.CallbackType;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

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
        var callbackQueryId = update.callbackQuery().Id();
        var answerCallbackQuery = AnswerCallbackQuery.defaultAnswer(callbackQueryId);
        client.answerCallbackQuery(answerCallbackQuery);
        ReplyMarkup callbackButton = new InlineKeyboardMarkup(
                List.of(
                        List.of(InlineKeyboardButton.callbackData(
                                CallbackType.SUBSCRIBE.getDescription(),
                                CallbackType.SUBSCRIBE.getName()
                        )),
                        List.of(InlineKeyboardButton.callbackData(
                                CallbackType.MANAGE.getDescription(),
                                CallbackType.MANAGE.getName()
                        )),
                        List.of(InlineKeyboardButton.callbackData(
                                CallbackType.SUBSCRIPTIONS.getDescription(),
                                CallbackType.SUBSCRIPTIONS.getName()
                        )),
                        List.of(InlineKeyboardButton.callbackData(
                                        CallbackType.INFO.getDescription(),
                                        CallbackType.INFO.getName()
                                ),
                                InlineKeyboardButton.callbackData(
                                        CallbackType.FEEDBACK.getDescription(),
                                        CallbackType.FEEDBACK.getName()
                                )
                        )
                )
        );
        var editMessageText = new EditMessageText(
                update.callbackQuery().message().chat().id(),
                update.callbackQuery().message().messageId(),
                messageSource.getMessage("start.menu.message", null, null),
                null,
                callbackButton
        );
        client.editMessageText(editMessageText);
    }

    @Override
    public CallbackType getType() {
        return CallbackType.START_MENU;
    }
}
