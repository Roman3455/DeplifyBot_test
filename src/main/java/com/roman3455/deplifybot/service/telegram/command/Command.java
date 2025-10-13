package com.roman3455.deplifybot.service.telegram.command;

import com.roman3455.deplifybot.dto.telegram.api.response.Update;

public interface Command {

    void apply(Update update);

    CommandType getType();
}
