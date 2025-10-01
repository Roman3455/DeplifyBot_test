package com.roman3455.deplifybot.service.telegram.command_handler.command;

import com.roman3455.deplifybot.client.TelegramClient;
import com.roman3455.deplifybot.dto.telegram.ReplyMarkup;
import com.roman3455.deplifybot.dto.telegram.SendMessage;
import com.roman3455.deplifybot.dto.telegram.Update;
import com.roman3455.deplifybot.dto.telegram.nested.InlineKeyboardButton;
import com.roman3455.deplifybot.dto.telegram.nested.InlineKeyboardMarkup;
import com.roman3455.deplifybot.service.telegram.callback_handler.CallbackType;
import com.roman3455.deplifybot.service.telegram.command_handler.CommandType;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public final class StartCommand implements Command {

    private final MessageSource messageSource;
    private final TelegramClient client;

    public StartCommand(final TelegramClient client, final MessageSource messageSource) {
        this.client = client;
        this.messageSource = messageSource;
    }

    @Override
    public SendMessage prepareMessage(final Update update) {
        long chatId = update.message().chat().id();
        String firstName = update.message().chat().firstName();
        String username = update.message().chat().username();
        String namePlaceholder = firstName == null ? username : firstName;
        String sourceText = messageSource
                .getMessage("start.message", new Object[]{namePlaceholder}, null)
                .formatted();
        ReplyMarkup callbackButton = new InlineKeyboardMarkup(
                List.of(List.of(InlineKeyboardButton.callbackData(
                        CallbackType.START_MENU.getDescription(),
                        CallbackType.START_MENU.getName()
                )))
        );
        return SendMessage.defaultMarkupMessage(chatId, sourceText, true, callbackButton);
    }

    @Override
    public void apply(final Update update) {
        client.sendMessage(prepareMessage(update));
    }

    @Override
    public CommandType getType() {
        return CommandType.START;
    }
}
