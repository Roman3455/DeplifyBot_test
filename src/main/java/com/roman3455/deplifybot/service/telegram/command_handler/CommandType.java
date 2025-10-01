package com.roman3455.deplifybot.service.telegram.command_handler;

public enum CommandType {
    START("/start", "Начало работы"),
    START_MENU("/start_menu", "Стартовое меню");

    private final String name;
    private final String description;

    CommandType(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
