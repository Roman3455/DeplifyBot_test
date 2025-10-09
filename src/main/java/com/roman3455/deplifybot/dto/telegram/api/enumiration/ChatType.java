package com.roman3455.deplifybot.dto.telegram.api.enumiration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.roman3455.deplifybot.util.enumiration.JsonEnum;
import com.roman3455.deplifybot.util.enumiration.JsonEnumUtil;

public enum ChatType implements JsonEnum {
    PRIVATE("private"),
    GROUP("group"),
    SUPERGROUP("supergroup"),
    CHANNEL("channel"),
    UNKNOWN("unknown");

    private final String value;

    ChatType(final String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }

    @JsonCreator
    public static ChatType fromValue(final String value) {
        return JsonEnumUtil.fromValue(ChatType.class, value, UNKNOWN);
    }
}
