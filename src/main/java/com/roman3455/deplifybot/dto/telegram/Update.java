package com.roman3455.deplifybot.dto.telegram;

import java.time.Instant;

/**
 * Deserialize-only DTO for incoming updates from Telegram.
 *
 * <p>At most one optional field may be present in a single incoming update.</p>
 *
 * @param updateId      Required. Unique identifier for the update. Starts from a positive number
 *                      and increases sequentially.
 * @param message       Optional. A new incoming message of any kind — text, photo, etc.
 * @param callbackQuery Optional. A new incoming callback query.
 * @param myChatMember  Optional. The bot’s chat member status was updated (in private chats — only when
 *                      the user blocks/unblocks the bot).
 * @see <a href="https://core.telegram.org/bots/api#update">Telegram Bot API: Update</a>
 */
public record Update(
        Long updateId,
        Message message,
        CallbackQuery callbackQuery,
        ChatMemberUpdated myChatMember
) {

    /**
     * Deserialize-only DTO for Incoming message from Telegram.
     *
     * <p>This object contains many optional fields; the actual set depends on the message type
     * (text, photo, etc.).</p>
     *
     * @param messageId         Required. Unique identifier inside the chat.
     * @param messageThreadId   Optional. Unique identifier of the forum topic; supergroups only.
     * @param from              Optional. The sender of the message; may be absent for messages sent to channels.
     * @param date              Required. Message date specified as a Unix time.
     * @param chat              Required. The chat to which the message belongs.
     * @param text              Optional. Text of the message in UTF-8.
     * @param migrateToChatId   Optional. The group has been migrated to a supergroup with the specified identifier.
     * @param migrateFromChatId Optional. The supergroup was migrated from a group with the specified identifier.
     * @param chatShared        Optional. Service message: a chat was shared with the bot.
     * @see <a href="https://core.telegram.org/bots/api#message">Telegram Bot API: Message</a>
     */
    public record Message(
            Long messageId,
            Long messageThreadId,
            User from,
            Instant date,
            Chat chat,
            String text,
            Long migrateToChatId,
            Long migrateFromChatId,
            ChatShared chatShared
    ) {
    }

    /**
     * Deserialize-only DTO for Incoming callback query from a callback button on an inline keyboard.
     *
     * <p>If the button that originated the query was attached to a message sent by the bot,
     * the {@code message} field will be present. If the button was attached to a message sent
     * via the bot (in inline mode), the {@code inline_message_id} field will be present.</p>
     *
     * @param Id   Required. Unique identifier for the query.
     * @param from Required. The sender.
     * @param data Optional. Data associated with the callback button.
     * @see com.roman3455.deplifybot.dto.telegram.markup.button.InlineKeyboardButton
     * @see com.roman3455.deplifybot.dto.telegram.markup.InlineKeyboardMarkup
     * @see <a href="https://core.telegram.org/bots/api#callbackquery">Telegram Bot API: CallbackQuery</a>
     */
    public record CallbackQuery(
            String Id,
            User from,
            String data
    ) {
    }

    /**
     * Deserialize-only DTO for Chat member status change.
     *
     * @param chat          Required. The chat to which the user belongs.
     * @param from          Required. The initiator of the action that caused the change.
     * @param date          Required. Change date as Unix time.
     * @param oldChatMember Required. Previous information about the chat member.
     * @param newChatMember Required. New information about the chat member.
     * @see <a href="https://core.telegram.org/bots/api#chatmemberupdated">Telegram Bot API: ChatMemberUpdated</a>
     */
    public record ChatMemberUpdated(
            Chat chat,
            User from,
            Instant date,
            ChatMember oldChatMember,
            ChatMember newChatMember
    ) {
    }

    /**
     * Deserialize-only DTO for a Telegram user or bot.
     *
     * @param id           Required. Unique identifier of the user or bot.
     * @param isBot        Required. {@code true} if this user is a bot.
     * @param firstName    Required. First name of the user or bot.
     * @param username     Optional. Username of the user or bot.
     * @param languageCode Optional. IETF language tag of the user’s language.
     * @see <a href="https://core.telegram.org/bots/api#user">Telegram Bot API: User</a>
     */
    public record User(
            Long id,
            boolean isBot,
            String firstName,
            String username,
            String languageCode
    ) {
    }

    /**
     * Deserialize-only DTO for a Telegram chat.
     *
     * @param id        Required. Unique identifier of the chat.
     * @param type      Required. Chat type; one of {@code private}, {@code group}, {@code supergroup},
     *                  or {@code channel}.
     * @param title     Optional. Title for supergroups, channels, and group chats.
     * @param username  Optional. Username for private chats, supergroups, and channels (if available).
     * @param firstName Optional. First name of the other party in a private chat.
     * @param isForum   Optional. {@code true} if the supergroup chat is a forum (topics are enabled).
     * @see <a href="https://core.telegram.org/bots/api#chat">Telegram Bot API: Chat</a>
     */
    public record Chat(
            Long id,
            String type,
            String title,
            String username,
            String firstName,
            Boolean isForum
    ) {
    }

    /**
     * Deserialize-only DTO for Information about a chat that was shared with the bot via KeyboardButtonRequestChat.
     *
     * @param requestId Required. Identifier of the request.
     * @param chatId    Required. Identifier of the shared chat.
     * @param title     Optional. Chat title, if it was requested by the bot.
     * @param username  Optional. Chat username, if it was requested by the bot and available.
     * @see com.roman3455.deplifybot.dto.telegram.markup.button.KeyboardButton
     * @see <a href="https://core.telegram.org/bots/api#chatshared">Telegram Bot API: ChatShared</a>
     */
    public record ChatShared(
            Long requestId,
            Long chatId,
            String title,
            String username
    ) {
    }

    /**
     * Deserialize-only DTO for Information about a single chat member.
     *
     * <p>The following six chat member statuses are supported:</p>
     * <ul>
     *   <li>creator — the chat owner, has all administrator rights.</li>
     *   <li>administrator — a chat member with additional privileges.</li>
     *   <li>member — a chat member without extra privileges or restrictions.</li>
     *   <li>restricted — a chat member with certain restrictions. Supergroups only.</li>
     *   <li>left — a user who is not currently a member of the chat but may rejoin.</li>
     *   <li>kicked — a user who is banned and cannot return to or view the chat.</li>
     * </ul>
     *
     * @param status Required. The member’s status in the chat.
     * @param user   Required. Information about the user.
     */
    public record ChatMember(
            String status,
            User user
    ) {
    }
}
