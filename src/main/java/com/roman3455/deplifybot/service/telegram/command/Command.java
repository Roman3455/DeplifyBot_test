package com.roman3455.deplifybot.service.telegram.command;

import com.roman3455.deplifybot.dto.telegram.api.request.SendMessage;
import com.roman3455.deplifybot.dto.telegram.inbound.Update;

public interface Command {

    SendMessage prepareMessage(Update update);

    void apply(Update update);

    CommandType getType();
}
