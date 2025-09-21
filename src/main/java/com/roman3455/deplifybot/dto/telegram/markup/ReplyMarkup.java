package com.roman3455.deplifybot.dto.telegram.markup;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

/**
 * Telegram Bot API reply markup (reply_markup).
 *
 * <p>A polymorphic, JSON-serializable container for reply markup variants, used as the value
 * of {@code replyMarkup} in {@link com.roman3455.deplifybot.dto.telegram.SendMessage}.</p>
 *
 * <p>Implementations:</p>
 * <ul>
 *   <li>{@link InlineKeyboardMarkup} — Inline keyboard</li>
 *   <li>{@link ReplyKeyboardMarkup} — Reply keyboard</li>
 *   <li>{@link ReplyKeyboardRemove} — Instruction to remove the reply keyboard</li>
 *   <li>{@link ForceReply} — Forces the user to reply</li>
 * </ul>
 *
 * <p>Type resolution is handled by Jackson via {@code @JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)}.</p>
 */
@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
public sealed interface ReplyMarkup permits
        InlineKeyboardMarkup, ReplyKeyboardMarkup, ReplyKeyboardRemove, ForceReply {
}
