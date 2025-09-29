package com.roman3455.deplifybot.dto.telegram.nested;

import com.roman3455.deplifybot.dto.telegram.ReplyMarkup;

public record ForceReply(
        boolean forceReply,
        String inputFieldPlaceholder,
        Boolean selective
) implements ReplyMarkup {

    public static final int MAX_PLACEHOLDER_LENGTH = 64;

    public ForceReply {
        if (!forceReply) {
            throw new IllegalArgumentException("Field 'force_reply' must be true.");
        }
        if (inputFieldPlaceholder != null
                && (inputFieldPlaceholder.isEmpty() || inputFieldPlaceholder.length() > MAX_PLACEHOLDER_LENGTH)) {
            throw new IllegalArgumentException("Allowed 'inputFieldPlaceholder' length is 1 to 64 characters.");
        }
    }

    public static ForceReply reply() {
        return new ForceReply(true, null, null);
    }

    public static ForceReply placeholder(final String inputFieldPlaceholder) {
        return new ForceReply(true, inputFieldPlaceholder, null);
    }
}
