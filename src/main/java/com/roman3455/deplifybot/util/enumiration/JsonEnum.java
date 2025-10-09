package com.roman3455.deplifybot.util.enumiration;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public interface JsonEnum {
    @JsonValue
    String getValue();

    @JsonCreator
    static <E extends Enum<E> & JsonEnum> E fromValue(Class<E> enumClass, String value, E defaultValue) {
        return JsonEnumUtil.fromValue(enumClass, value, defaultValue);
    }
}
