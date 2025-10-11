package com.roman3455.deplifybot.dto.telegram.api.enumiration;

import com.roman3455.deplifybot.util.enumiration.JsonEnum;

public enum ChatMemberStatusType implements JsonEnum {
    ADMINISTRATOR("administrator"),
    CREATOR("creator"),
    KICKED("kicked"),
    LEFT("left"),
    MEMBER("member"),
    RESTRICTED("restricted");

    private final String value;

    ChatMemberStatusType(final String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
