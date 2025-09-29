package com.roman3455.deplifybot.service.telegram.command_handler;

import com.roman3455.deplifybot.dto.telegram.Update;

public interface CommandHandler {

    void handle(Update update);
}
