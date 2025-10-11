package com.roman3455.deplifybot.service.telegram.command.cmd;

import com.roman3455.deplifybot.client.TelegramClient;
import com.roman3455.deplifybot.dto.telegram.api.ui.ReplyMarkup;
import com.roman3455.deplifybot.dto.telegram.api.request.SendMessage;
import com.roman3455.deplifybot.dto.telegram.api.response.Update;
import com.roman3455.deplifybot.dto.telegram.api.ui.InlineKeyboardButton;
import com.roman3455.deplifybot.dto.telegram.api.ui.InlineKeyboardMarkup;
import com.roman3455.deplifybot.service.telegram.callback.CallbackType;
import com.roman3455.deplifybot.service.telegram.command.Command;
import com.roman3455.deplifybot.service.telegram.command.CommandType;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class StartMenuCommand implements Command {

    private final MessageSource messageSource;
    private final TelegramClient client;

    public StartMenuCommand(final TelegramClient client, final MessageSource messageSource) {
        this.client = client;
        this.messageSource = messageSource;
    }

    @Override
    public SendMessage prepareMessage(final Update update) {
        long chatId = update.message().chat().id();
        String sourceText = messageSource.getMessage("start.menu.message", null, null);
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
        return SendMessage.ofSilentMarkdownMessage(chatId, sourceText, callbackButton);
    }

    @Override
    public void apply(final Update update) {
        client.sendMessage(prepareMessage(update));
    }

    @Override
    public CommandType getType() {
        return CommandType.START_MENU;
    }
}
