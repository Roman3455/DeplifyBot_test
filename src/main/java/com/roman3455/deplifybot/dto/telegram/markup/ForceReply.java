package com.roman3455.deplifybot.dto.telegram.markup;

/**
 * Serialize-only DTO for Forces the user to reply to the bot's message.
 *
 * <p>Asks the Telegram client to open the reply UI for a specific bot message — as if the user had manually pressed
 * “Reply”. Useful for step-by-step flows, especially with privacy mode enabled, to ensure replies are sent to the
 * exact message you expect.</p>
 *
 * @param forceReply            Required. Must be {@code true}.
 * @param inputFieldPlaceholder Optional. Placeholder shown in the input field while the keyboard is active;
 *                              1–64 characters.
 * @param selective             Optional. Show the reply UI only to specific users:
 *                              (1) users mentioned via @username in the {@code Message.text} field;
 *                              (2) if the bot’s message is a reply in the same chat and forum topic, the sender of
 *                              the original message.
 *                              <i>Example: a user asks to change the bot’s language, the bot responds with a keyboard
 *                              to choose a language — other users in the group will not see it.</i>
 * @see <a href="https://core.telegram.org/bots/api#forcereply">Telegram Bot API: ForceReply</a>
 */
public record ForceReply(
        boolean forceReply,
        String inputFieldPlaceholder,
        Boolean selective
) implements ReplyMarkup {

    /**
     * Maximum placeholder length (in characters).
     */
    public static final int MAX_PLACEHOLDER_LENGTH = 64;

    /**
     * ForceReply validation.
     */
    public ForceReply {
        if (!forceReply) {
            throw new IllegalArgumentException("Field 'force_reply' must be true.");
        }
        if (inputFieldPlaceholder != null
                && (inputFieldPlaceholder.isEmpty() || inputFieldPlaceholder.length() > MAX_PLACEHOLDER_LENGTH)) {
            throw new IllegalArgumentException("Allowed 'inputFieldPlaceholder' length is 1 to 64 characters.");
        }
    }

    /**
     * Creates a ForceReply that forces the user to reply to the bot's message.
     */
    public static ForceReply reply() {
        return new ForceReply(true, null, null);
    }

    /**
     * Creates a ForceReply with the specified input field placeholder.
     */
    public static ForceReply placeholder(final String inputFieldPlaceholder) {
        return new ForceReply(true, inputFieldPlaceholder, null);
    }
}
