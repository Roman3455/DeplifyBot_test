package com.roman3455.deplifybot.dto.telegram.api.enumiration;

import com.roman3455.deplifybot.util.enumiration.JsonEnum;

public enum ParseModeType implements JsonEnum {
    MARKDOWN_V2("MarkdownV2"),
    HTML("HTML"),
    PLAIN("");

    private final String value;

    ParseModeType(final String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
