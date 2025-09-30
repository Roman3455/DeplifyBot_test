package com.roman3455.deplifybot.service.telegram.callback_handler;

public enum CallbackType {
    START_MENU("#start_menu", "Главное меню"),
    MENU("#menu", "Вернуться в главное меню");

    private final String name;
    private final String description;

    CallbackType(String name, String description) {
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
