package com.roman3455.deplifybot.dto.telegram.markup;

import com.roman3455.deplifybot.dto.telegram.markup.button.InlineKeyboardButton;
import jakarta.annotation.Nonnull;

import java.util.List;
import java.util.Objects;

/**
 * Serialize-only DTO for Inline keyboard markup.
 *
 * <p>A set of buttons displayed beneath a message. It does not replace the system keyboard and sends an action
 * when a button is pressed. Supported in all chat types, including channels.</p>
 *
 * @param inlineKeyboard Required. A two-dimensional list; each inner list represents a single row of buttons
 *                       composed of {@link InlineKeyboardButton} objects.
 * @see <a href="https://core.telegram.org/bots/api#inlinekeyboardmarkup">Telegram Bot API: InlineKeyboardMarkup</a>
 */
public record InlineKeyboardMarkup(@Nonnull List<List<InlineKeyboardButton>> inlineKeyboard) implements ReplyMarkup {

    /**
     * InlineKeyboardMarkup validation.
     */
    public InlineKeyboardMarkup {
        Objects.requireNonNull(inlineKeyboard, "Field 'inlineKeyboard' must not be null.");
    }
}
