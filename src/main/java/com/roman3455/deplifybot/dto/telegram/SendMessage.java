package com.roman3455.deplifybot.dto.telegram;

import com.roman3455.deplifybot.dto.telegram.markup.ReplyMarkup;
import jakarta.annotation.Nonnull;

import java.util.Objects;

/**
 * Serialize-only DTO for sending text messages.
 *
 * <p>On success, the scent message is returned in {@link Update#message()}.</p>
 *
 * @param chatId              Required. Unique identifier of the target chat or the target channel’s
 *                            <code>@channelUsername</code>.
 * @param messageThreadId     Optional. Unique identifier of the forum topic (topic) — applicable to
 *                            forum supergroups only.
 * @param text                Required. Message text to send; 1–4096 characters after entity parsing.
 * @param parseMode           Optional. Message parse mode; one of {@code HTML}, {@code Markdown},
 *                            {@code MarkdownV2}.
 * @param disableNotification Optional. Send the message silently (without sound).
 * @param replyMarkup         Optional. Additional reply interface: inline keyboard, reply keyboard,
 *                            keyboard removal, or force-reply prompt.
 * @see <a href="https://core.telegram.org/bots/api#sendmessage">Telegram Bot API: sendMessage</a>
 */
public record SendMessage(
        @Nonnull Object chatId,
        Long messageThreadId,
        @Nonnull String text,
        String parseMode,
        Boolean disableNotification,
        ReplyMarkup replyMarkup
) {

    /**
     * Maximum message text length (in characters).
     */
    public static final int MAX_TEXT_LENGTH = 4096;

    /**
     * SendMessage validation.
     */
    public SendMessage {
        Objects.requireNonNull(chatId, "Field 'chatId' must not be null.");
        Objects.requireNonNull(text, "Field 'text' must not be null.");
        if (text.isEmpty() || text.length() > MAX_TEXT_LENGTH) {
            throw new IllegalArgumentException("Allowed 'text' length is 1 to 4096 characters.");
        }
    }
}
