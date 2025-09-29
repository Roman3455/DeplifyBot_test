package com.roman3455.deplifybot.service.telegram.command_handler.impl;

import com.roman3455.deplifybot.dto.telegram.Update;
import com.roman3455.deplifybot.service.telegram.command_handler.CommandHandler;
import com.roman3455.deplifybot.service.telegram.command_handler.command.Command;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class CommandHandlerImpl implements CommandHandler {

    Map<String, Command> commandsMap;

    public CommandHandlerImpl(List<Command> commands) {
        this.commandsMap = new HashMap<>();
        commands.forEach(command -> commandsMap.put(command.getType().getName(), command));
    }

    @Override
    public void handle(Update update) {
        String commandName = update.message().text().strip().toLowerCase(Locale.ROOT);
        Command command = commandsMap.get(commandName);
        if (command != null) {
            command.apply(update);
        }
    }
}
