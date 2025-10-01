package com.roman3455.deplifybot.service.telegram.callback_handler.callback;

import com.roman3455.deplifybot.client.TelegramClient;
import com.roman3455.deplifybot.dto.telegram.AnswerCallbackQuery;
import com.roman3455.deplifybot.dto.telegram.EditMessageText;
import com.roman3455.deplifybot.dto.telegram.ReplyMarkup;
import com.roman3455.deplifybot.dto.telegram.Update;
import com.roman3455.deplifybot.dto.telegram.nested.InlineKeyboardButton;
import com.roman3455.deplifybot.dto.telegram.nested.InlineKeyboardMarkup;
import com.roman3455.deplifybot.service.telegram.callback_handler.CallbackType;
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
        var answerCallbackQuery = AnswerCallbackQuery.defaultAnswerCallbackQuery(callbackQueryId);
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
                null,
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
