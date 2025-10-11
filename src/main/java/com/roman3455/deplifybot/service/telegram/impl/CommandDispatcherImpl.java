package com.roman3455.deplifybot.service.telegram.impl;

import com.roman3455.deplifybot.dto.telegram.api.response.Update;
import com.roman3455.deplifybot.service.telegram.CommandDispatcher;
import com.roman3455.deplifybot.service.telegram.command.Command;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public final class CommandDispatcherImpl implements CommandDispatcher {

    private final Map<String, Command> commandsMap;

    public CommandDispatcherImpl(final List<Command> commands) {
        this.commandsMap = new HashMap<>();
        commands.forEach(command -> commandsMap.put(command.getType().getName(), command));
    }

    @Override
    public void dispatch(final Update update) {
        String commandName = update.message().text().strip().toLowerCase(Locale.ROOT);
        Command command = commandsMap.get(commandName);
        if (command != null) {
            command.apply(update);
        }
    }
}
