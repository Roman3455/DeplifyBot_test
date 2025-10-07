package com.roman3455.deplifybot.service.telegram.impl;

import com.roman3455.deplifybot.dto.telegram.inbound.Update;
import com.roman3455.deplifybot.service.telegram.CallbackDispatcher;
import com.roman3455.deplifybot.service.telegram.CommandDispatcher;
import com.roman3455.deplifybot.service.telegram.UpdateDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public final class UpdateDispatcherImpl implements UpdateDispatcher {

    private static final Logger LOGGER = LoggerFactory.getLogger(UpdateDispatcherImpl.class);

    private final CommandDispatcher commandDispatcher;
    private final CallbackDispatcher callbackDispatcher;

    public UpdateDispatcherImpl(final CommandDispatcher commandDispatcher, final CallbackDispatcher callbackDispatcher) {
        this.commandDispatcher = commandDispatcher;
        this.callbackDispatcher = callbackDispatcher;
    }

    @Override
    public void dispatch(final Update update) {
        if (update.hasMessage(update) && update.message().hasText(update.message())) {
            commandDispatcher.dispatch(update);
            return;
        }
        if (update.hasCallbackQuery(update)) {
            callbackDispatcher.dispatch(update);
            return;
        }
        LOGGER.warn("Unknown Update received: [{}]", update.updateId());
    }
}
