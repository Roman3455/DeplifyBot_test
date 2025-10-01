package com.roman3455.deplifybot.service.telegram.command_handler.command;

import com.roman3455.deplifybot.dto.telegram.SendMessage;
import com.roman3455.deplifybot.dto.telegram.Update;
import com.roman3455.deplifybot.service.telegram.command_handler.CommandType;

public interface Command {

    SendMessage prepareMessage(Update update);

    void apply(Update update);

    CommandType getType();
}
