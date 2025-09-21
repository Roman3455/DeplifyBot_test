package com.roman3455.deplifybot.dto.telegram.markup.button;

import jakarta.annotation.Nonnull;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * Serialize-only DTO for Inline Keyboard Button.
 *
 * <p>This model enforces that exactly one of the supported optional fields is present: {@code url},
 * {@code callback_data}, {@code copy_text}.</p>
 * <p>Note: the Telegram Bot API defines additional fields ({@code web_app}, {@code login_url},
 * {@code switch_inline_query}, {@code switch_inline_query_current_chat}, {@code switch_inline_query_chosen_chat},
 * {@code callback_game}, {@code pay}). They are not implemented here. Introduce dedicated types if you need them.</p>
 *
 * @param text         Required. The label displayed on the button.
 * @param url          Optional. An "http"/"https" or "tg://" link opened when the button is pressed. Links of the form
 *                     {@code tg://user?id=<user_id>} can be used to mention a user by ID, subject to privacy settings.
 * @param callbackData Optional. Data sent back to the bot in a callback query; 1–64 bytes (UTF-8).
 * @param copyText     Optional. Copy-to-clipboard payload containing the text to be copied by the client.
 * @see <a href="https://core.telegram.org/bots/api#callbackquery">CallbackQuery</a>
 * @see <a href="https://core.telegram.org/bots/api#inlinekeyboardbutton">Telegram Bot API: InlineKeyboardButton</a>
 */
public record InlineKeyboardButton(
        @Nonnull String text,
        String url,
        String callbackData,
        CopyTextButton copyText
) {

    /**
     * Maximum callback data length (in bytes).
     */
    public static final int MAX_CALLBACK_DATA_LENGTH = 64;

    /**
     * InlineKeyboardButton validation.
     */
    public InlineKeyboardButton {
        Objects.requireNonNull(text, "Field 'text' must not be null.");
        int filledFields = 0;
        if (url != null) {
            filledFields++;
        }
        if (callbackData != null) {
            filledFields++;
            boolean isGreaterThanMax = callbackData.getBytes(StandardCharsets.UTF_8).length > MAX_CALLBACK_DATA_LENGTH;
            if (callbackData.isEmpty() || isGreaterThanMax) {
                throw new IllegalArgumentException("Field 'callbackData' must be between 1 and 64 bytes in UTF-8.");
            }
        }
        if (copyText != null) {
            filledFields++;
        }
        if (filledFields != 1) {
            throw new IllegalArgumentException("Exactly one of 'url', 'callbackData', 'copyText' must be present.");
        }
    }

    /**
     * Serialize-only DTO for "Copy text" button.
     *
     * @param text Required. The text the client should copy to the clipboard; 1–256 characters.
     */
    public record CopyTextButton(
            @Nonnull String text
    ) {

        /**
         * Maximum text length (in characters).
         */
        public static final int MAX_TEXT_LENGTH = 256;

        /**
         * CopyTextButton validation.
         */
        public CopyTextButton {
            Objects.requireNonNull(text, "Field 'text' must not be null.");
            if (text.isEmpty() || text.length() > MAX_TEXT_LENGTH) {
                throw new IllegalArgumentException("Allowed 'text' length is 1 to 256 characters.");
            }
        }
    }

    /**
     * Create a URL button.
     */
    public static InlineKeyboardButton url(@Nonnull final String text, @Nonnull final String url) {
        return new InlineKeyboardButton(text, url, null, null);
    }

    /**
     * Create a Callback Data button.
     */
    public static InlineKeyboardButton callbackData(@Nonnull final String text, @Nonnull final String callbackData) {
        return new InlineKeyboardButton(text, null, callbackData, null);
    }

    /**
     * Create a Copy Text button.
     */
    public static InlineKeyboardButton copyText(@Nonnull final String text, @Nonnull final String copyText) {
        return new InlineKeyboardButton(text, null, null, new CopyTextButton(copyText));
    }
}
