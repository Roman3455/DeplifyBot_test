package com.roman3455.deplifybot.service.telegram.command.impl;

import com.roman3455.deplifybot.dto.telegram.api.ui.ReplyMarkup;
import com.roman3455.deplifybot.dto.telegram.api.response.Update;
import com.roman3455.deplifybot.dto.telegram.factory.InlineKeyboardFactory;
import com.roman3455.deplifybot.dto.telegram.factory.InlineKeyboardType;
import com.roman3455.deplifybot.service.telegram.TelegramClientService;
import com.roman3455.deplifybot.service.telegram.command.Command;
import com.roman3455.deplifybot.service.telegram.command.CommandType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public final class StartCommand implements Command {

    private static final Logger LOG = LoggerFactory.getLogger(StartCommand.class);

    private final MessageSource messageSource;
    private final TelegramClientService clientService;

    public StartCommand(final TelegramClientService clientService, final MessageSource messageSource) {
        this.clientService = clientService;
        this.messageSource = messageSource;
    }

    @Override
    public void apply(final Update update) {
        long chatId = update.message().chat().id();
        String firstName = update.message().chat().firstName();
        String username = update.message().chat().username();
        String namePlaceholder = firstName == null ? username : firstName;
        String sourceText = messageSource.getMessage("start.message", new Object[]{namePlaceholder}, null);
        ReplyMarkup inlineKeyboardMarkup = InlineKeyboardFactory.create(InlineKeyboardType.START_MENU_KEYBOARD);
        var response = clientService.sendPrivatePlainMessage(chatId, sourceText, inlineKeyboardMarkup);
        if (!response.ok()) {
            LOG.warn("Update [{}]: Failed to send /start message. Error code {}, {}",
                    update.updateId(), response.errorCode(), response.description()
            );
        } else {
            LOG.info("Update [{}]: Successfully sent /start message.", update.updateId());
        }
    }

    @Override
    public CommandType getType() {
        return CommandType.START;
    }
}
