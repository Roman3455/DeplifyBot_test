package com.roman3455.deplifybot.service.telegram.command_handler.command;

import com.roman3455.deplifybot.client.TelegramClient;
import com.roman3455.deplifybot.dto.telegram.Update;
import com.roman3455.deplifybot.service.telegram.command_handler.CommandType;
import org.springframework.stereotype.Service;

@Service
public class StartCommand implements Command {

    TelegramClient client;

    public StartCommand(TelegramClient client) {
        this.client = client;
    }

    @Override
    public void apply(Update update) {
        var chatId = update.message().chat().id();
    }

    @Override
    public CommandType getType() {
        return CommandType.START;
    }
}
