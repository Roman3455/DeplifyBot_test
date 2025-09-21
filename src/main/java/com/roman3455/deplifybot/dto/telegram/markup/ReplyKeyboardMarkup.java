package com.roman3455.deplifybot.dto.telegram.markup;

import com.roman3455.deplifybot.dto.telegram.markup.button.KeyboardButton;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Objects;

/**
 * Serialize-only DTO for Reply keyboard markup with suggested replies.
 *
 * <p>Replaces the system keyboard. Each nested collection in {@code keyboard} represents a single row of buttons.
 * Not supported in channels or for messages sent on behalf of a Telegram Business account.</p>
 *
 * @param keyboard              Required. A two-dimensional list where each inner list represents one row of
 *                              {@link KeyboardButton} items.
 * @param isPersistent          Optional (defaults to {@code false}). Asks the client to always display this keyboard
 *                              when the regular keyboard is hidden; if {@code false}, the user can toggle it with
 *                              the icon.
 * @param resizeKeyboard        Optional (defaults to {@code false}). Asks the client to adjust the keyboard’s height
 *                              for optimal fit.
 * @param oneTimeKeyboard       Optional (defaults to {@code false}). Asks the client to hide the keyboard after use
 *                              and show the letter keyboard.
 * @param inputFieldPlaceholder Optional. Placeholder text shown in the input field while the keyboard is active;
 *                              1–64 characters.
 * @param selective             Optional. Show the keyboard only to specific users:
 *                              (1) users mentioned via @username in the {@code Message.text} field;
 *                              (2) if the bot’s message is a reply in the same chat and forum topic, the sender of
 *                              the original message.
 *                              <i>Example: a user requests to change the bot’s language, the bot responds with a
 *                              keyboard to choose a language — other users in the group won’t see it.</i>
 * @see <a href="https://core.telegram.org/bots/api#replykeyboardmarkup">Telegram Bot API: ReplyKeyboardMarkup</a>
 */
public record ReplyKeyboardMarkup(
        @Nonnull List<List<KeyboardButton>> keyboard,
        Boolean isPersistent,
        Boolean resizeKeyboard,
        Boolean oneTimeKeyboard,
        String inputFieldPlaceholder,
        Boolean selective
) implements ReplyMarkup {

    /**
     * Maximum placeholder length (in characters).
     */
    public static final int MAX_PLACEHOLDER_LENGTH = 64;

    /**
     * ReplyKeyboardMarkup validation.
     */
    public ReplyKeyboardMarkup {
        Objects.requireNonNull(keyboard, "Field 'keyboard' must not be null.");
        if (inputFieldPlaceholder.isEmpty() || inputFieldPlaceholder.length() > MAX_PLACEHOLDER_LENGTH) {
            throw new IllegalArgumentException("Allowed 'inputFieldPlaceholder' length is 1 to 64 characters.");
        }
    }

    /**
     * Returns a reply keyboard markup with convenient defaults.
     */
    public static ReplyKeyboardMarkup standardMarkup(
            final List<List<KeyboardButton>> keyboard,
            final Boolean resizeKeyboard,
            final Boolean oneTimeKeyboard
    ) {
        return new ReplyKeyboardMarkup(
                keyboard, null, resizeKeyboard, oneTimeKeyboard, null, null
        );
    }
}
