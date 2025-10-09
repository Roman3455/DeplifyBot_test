package com.roman3455.deplifybot.util.enumiration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonEnumUtil {

    private static final Logger LOG = LoggerFactory.getLogger(JsonEnumUtil.class);

    private JsonEnumUtil() {
    }

    public static <T extends Enum<T> & JsonEnum> T fromValue(
            final Class<T> enumClass,
            final String value,
            final T defaultValue
    ) {
        if (value == null) {
            return defaultValue;
        }
        for (T constant : enumClass.getEnumConstants()) {
            if (constant.getValue().equalsIgnoreCase(value)) {
                return constant;
            }
        }

        LOG.warn("Unknown enum value '{}' for {}", value, enumClass.getSimpleName());
        return defaultValue;
    }
}
