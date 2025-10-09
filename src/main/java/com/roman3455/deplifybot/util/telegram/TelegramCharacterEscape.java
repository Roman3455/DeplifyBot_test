package com.roman3455.deplifybot.util.telegram;

import java.util.regex.Pattern;

public final class TelegramCharacterEscape {

    private static final Pattern MARKDOWN_V2_SPECIALS_PATTERN = Pattern.compile("([_\\[\\]()~`>#+=\\-|{}.!*])");

    private TelegramCharacterEscape() {
    }

    public static String escapeMarkdownV2(final String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        return MARKDOWN_V2_SPECIALS_PATTERN.matcher(text).replaceAll("\\\\$1");
    }

    public static String escapeHtml(final String text) {
        if (text == null || text.isEmpty()) {
            return "";
        }
        return text
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }
}
