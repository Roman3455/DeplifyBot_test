package com.roman3455.deplifybot.service.telegram.command_handler.command;

import com.roman3455.deplifybot.dto.telegram.outbound.SendMessage;
import com.roman3455.deplifybot.dto.telegram.inbound.Update;
import com.roman3455.deplifybot.service.telegram.command_handler.CommandType;

public interface Command {

    SendMessage prepareMessage(Update update);

    void apply(Update update);

    CommandType getType();
}
