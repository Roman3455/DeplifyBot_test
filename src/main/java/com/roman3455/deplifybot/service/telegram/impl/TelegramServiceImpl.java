package com.roman3455.deplifybot.service.telegram.impl;

import com.roman3455.deplifybot.dto.telegram.api.response.Update;
import com.roman3455.deplifybot.service.telegram.callback.CallbackDispatcher;
import com.roman3455.deplifybot.service.telegram.command.CommandDispatcher;
import com.roman3455.deplifybot.service.telegram.TelegramService;
import com.roman3455.deplifybot.service.telegram.message.MessageDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public final class TelegramServiceImpl implements TelegramService {

    private static final Logger LOG = LoggerFactory.getLogger(TelegramServiceImpl.class);

    private final CommandDispatcher commandDispatcher;
    private final CallbackDispatcher callbackDispatcher;
    private final MessageDispatcher messageDispatcher;

    public TelegramServiceImpl(
            final CommandDispatcher commandDispatcher,
            final CallbackDispatcher callbackDispatcher,
            final MessageDispatcher messageDispatcher
    ) {
        this.commandDispatcher = commandDispatcher;
        this.callbackDispatcher = callbackDispatcher;
        this.messageDispatcher = messageDispatcher;
    }

    @Override
    public void processUpdate(final Update update) {
        if (update == null) {
            LOG.warn("Received update is null");
            return;
        }

        if (update.hasMessage() && update.message().hasText()) {
            String text = update.message().text();
            if (text.startsWith("/")) {
                commandDispatcher.dispatch(update);
                LOG.info("Update [{}]: Received command update", update.updateId());
            } else {
                messageDispatcher.dispatch(update);
                LOG.info("Update [{}]: Received message update", update.updateId());
            }
            return;
        }
        if (update.hasCallbackQuery()) {
            callbackDispatcher.dispatch(update);
            LOG.info("Update [{}]: Received callback update", update.updateId());
            return;
        }
        LOG.warn("Update [{}]: Unknown update received", update.updateId());
    }
}
