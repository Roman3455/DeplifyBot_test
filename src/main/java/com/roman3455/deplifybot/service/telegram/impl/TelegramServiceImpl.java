package com.roman3455.deplifybot.service.telegram.impl;

import com.roman3455.deplifybot.dto.telegram.api.response.Update;
import com.roman3455.deplifybot.service.telegram.CallbackDispatcher;
import com.roman3455.deplifybot.service.telegram.CommandDispatcher;
import com.roman3455.deplifybot.service.telegram.TelegramService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public final class TelegramServiceImpl implements TelegramService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TelegramServiceImpl.class);

    private final CommandDispatcher commandDispatcher;
    private final CallbackDispatcher callbackDispatcher;

    public TelegramServiceImpl(
            final CommandDispatcher commandDispatcher,
            final CallbackDispatcher callbackDispatcher
    ) {
        this.commandDispatcher = commandDispatcher;
        this.callbackDispatcher = callbackDispatcher;
    }

    @Override
    public void dispatch(final Update update) {
        if (update.hasMessage() && update.message().hasText()) {
            commandDispatcher.dispatch(update);
            return;
        }
        if (update.hasCallbackQuery()) {
            callbackDispatcher.dispatch(update);
            return;
        }
        LOGGER.warn("Unknown Update received: [{}]", update.updateId());
    }
}
