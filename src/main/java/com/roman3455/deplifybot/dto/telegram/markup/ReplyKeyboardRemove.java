package com.roman3455.deplifybot.dto.telegram.markup;

/**
 * Serialize-only DTO for Instruction to remove the reply keyboard.
 *
 * <p>Asks the Telegram client to remove the custom reply keyboard and show the standard
 * letter keyboard. By default, a reply keyboard remains visible until the bot sends a new one.
 * An exception is one-time keyboards (see
 * {@link com.roman3455.deplifybot.dto.telegram.markup.ReplyKeyboardMarkup}), which are hidden
 * immediately after a button is pressed. Not supported in channels or for messages sent on behalf
 * of a Telegram Business account.</p>
 *
 * @param removeKeyboard Required. Removes the custom keyboard (the user will not be able to summon it).
 * @param selective      Optional. Remove the keyboard only for specific users:
 *                       (1) users mentioned via <code>@username</code> in the {@code Message.text} field;
 *                       (2) if the bot’s message is a reply in the same chat and forum topic,
 *                       the sender of the original message.
 *                       <i>Example:</i> a user votes in a poll — the bot sends a confirmation and removes
 *                       the keyboard only for this user, keeping it for others who haven’t voted yet.
 * @see <a href="https://core.telegram.org/bots/api#replykeyboardremove">Telegram Bot API: ReplyKeyboardRemove</a>
 */
public record ReplyKeyboardRemove(boolean removeKeyboard, Boolean selective) implements ReplyMarkup {

    /**
     * ReplyKeyboardRemove validation.
     */
    public ReplyKeyboardRemove {
        if (!removeKeyboard) {
            throw new IllegalArgumentException("Field 'removeKeyboard' must be true.");
        }
    }

    /**
     * Remove the keyboard for all users.
     */
    public static ReplyKeyboardRemove allUsers() {
        return new ReplyKeyboardRemove(true, null);
    }

    /**
     * Remove the keyboard selectively.
     */
    public static ReplyKeyboardRemove selectiveUsers() {
        return new ReplyKeyboardRemove(true, true);
    }
}
