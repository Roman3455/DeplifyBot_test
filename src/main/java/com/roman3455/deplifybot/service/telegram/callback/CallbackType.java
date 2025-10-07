package com.roman3455.deplifybot.service.telegram.callback;

import com.roman3455.deplifybot.service.telegram.BotActionType;

public enum CallbackType implements BotActionType {
    START_MENU("#start_menu", "Главное меню"),
    MENU("#menu", "Вернуться в главное меню"),
    SUBSCRIBE("#subscribe", "Подписаться на события"),
    MANAGE("#manage", "Управлять подписками"),
    SUBSCRIPTIONS("#subscriptions", "Посмотреть подписки"),
    INFO("#info", "Info❓"),
    FEEDBACK("#feedback", "Обратная связь \uD83D\uDCE9");

    private final String name;
    private final String description;

    CallbackType(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }
}
