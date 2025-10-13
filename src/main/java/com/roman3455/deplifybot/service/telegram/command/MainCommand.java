package com.roman3455.deplifybot.service.telegram.command;

import com.roman3455.deplifybot.dto.telegram.api.response.Update;
import com.roman3455.deplifybot.dto.telegram.api.ui.ReplyMarkup;
import com.roman3455.deplifybot.dto.telegram.factory.InlineKeyboardFactory;
import com.roman3455.deplifybot.dto.telegram.factory.InlineKeyboardType;
import com.roman3455.deplifybot.service.telegram.TelegramClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public final class MainCommand implements Command {

    private static final Logger LOG = LoggerFactory.getLogger(MainCommand.class);

    private final MessageSource messageSource;
    private final TelegramClientService clientService;

    public MainCommand(final MessageSource messageSource, final TelegramClientService clientService) {
        this.messageSource = messageSource;
        this.clientService = clientService;
    }

    @Override
    public void apply(final Update update) {
        long chatId = update.message().chat().id();
        String sourceText = messageSource.getMessage("main.message", null, null);
        ReplyMarkup inlineKeyboardMarkup = InlineKeyboardFactory.create(InlineKeyboardType.MENU_KEYBOARD);
        var response = clientService.sendPrivatePlainMessage(chatId, sourceText, inlineKeyboardMarkup);
        if (!response.ok()) {
            LOG.warn("Update [{}]: Failed to send /main message. Error code {}, {}",
                    update.updateId(), response.errorCode(), response.description()
            );
        } else {
            LOG.info("Update [{}]: Successfully sent /main message.", update.updateId());
        }
    }

    @Override
    public CommandType getType() {
        return CommandType.MAIN;
    }
}
