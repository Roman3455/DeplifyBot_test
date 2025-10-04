package com.roman3455.deplifybot.service.telegram.impl;

import com.roman3455.deplifybot.dto.telegram.inbound.Update;
import com.roman3455.deplifybot.service.telegram.callback_handler.CallbackHandler;
import com.roman3455.deplifybot.service.telegram.command_handler.CommandHandler;
import com.roman3455.deplifybot.service.telegram.UpdateDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public final class UpdateDispatcherImpl implements UpdateDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateDispatcherImpl.class);

    private final CommandHandler commandHandler;
    private final CallbackHandler callbackHandler;

    public UpdateDispatcherImpl(final CommandHandler commandHandler, final CallbackHandler callbackHandler) {
        this.commandHandler = commandHandler;
        this.callbackHandler = callbackHandler;
    }

    @Override
    public void route(final Update update) {
        if (update.hasMessage(update) && update.message().hasText(update.message())) {
            commandHandler.handle(update);
            return;
        }
        if (update.hasCallbackQuery(update)) {
            callbackHandler.handle(update);
            return;
        }
        LOGGER.warn("Unknown Update received: [{}]", update.updateId());
    }
}
