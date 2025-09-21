package com.roman3455.deplifybot.dto.telegram.markup.button;

import jakarta.annotation.Nonnull;

import java.util.Objects;

/**
 * Serialize-only DTO for Reply keyboard button.
 *
 * <p>At most one of the optional button-type fields may be specified: {@code request_users}, {@code request_chat},
 * {@code request_contact}, {@code request_location}, {@code request_poll}, or {@code web_app}. For a plain text
 * button, you may pass a string with the button {@code text} instead of an object.</p>
 *
 * @param text        Required. Button label. If none of the optional fields is set, pressing the button will send
 *                    this text as a message.
 * @param requestChat Optional. Opens a list of suitable chats; the identifier of the chosen chat will be delivered to
 *                    the bot in a service message {@code chat_shared}. Available in private chats only.
 * @see <a href="https://core.telegram.org/bots/api#keyboardbutton">Telegram Bot API: KeyboardButton</a>
 */
public record KeyboardButton(
        @Nonnull String text,
        KeyboardButtonRequestChat requestChat
) {

    /**
     * KeyboardButton validation.
     */
    public KeyboardButton {
        Objects.requireNonNull(text, "Field 'text' must not be null.");
    }

    /**
     * Serialize-only DTO for Criteria for requesting a suitable chat.
     *
     * <p>Information about the selected chat will be delivered to the bot when the corresponding button is pressed.
     * If needed, the bot and/or the user may be required to have the specified administrator rights.</p>
     *
     * @param requestId               Required. Unique identifier of the request that will be returned in the
     *                                {@code ChatShared} object. Must be unique within a single message.
     * @param chatIsChannel           Required. {@code true} — request a channel; {@code false} — request a
     *                                group/supergroup.
     * @param userAdministratorRights Optional. Required administrator rights for the user in the target chat. Must be
     *                                a superset of {@code botAdministratorRights}. If omitted, no restriction applies.
     * @param botAdministratorRights  Required administrator rights for the bot in the target chat. Must be a subset
     *                                of {@code userAdministratorRights}. If omitted, no restriction applies.
     * @param botIsMember             Optional. {@code true} — request a chat where the bot is already a member.
     * @param requestTitle            Optional. {@code true} — request the chat title.
     * @see <a href="https://core.telegram.org/bots/api#keyboardbuttonrequestchat">
     * Telegram Bot API: KeyboardButtonRequestChat</a>
     */
    public record KeyboardButtonRequestChat(
            @Nonnull Long requestId,
            boolean chatIsChannel,
            ChatAdministratorRights userAdministratorRights,
            ChatAdministratorRights botAdministratorRights,
            Boolean botIsMember,
            Boolean requestTitle
    ) {

        public KeyboardButtonRequestChat {
            Objects.requireNonNull(requestId, "Field 'request_id' must not be null.");
        }
    }

    /**
     * Serialize-only DTO for Administrator rights in a chat.
     *
     * <p>Describes permissions for both a user administrator and the bot. Some fields apply only to
     * channels/groups/supergroups/forum topics — when the chat type does not match, the respective right is ignored
     * by the client/server.</p>
     *
     * @param isAnonymous             Required. If {@code true}, the administrator’s presence is hidden (anonymous
     *                                admin).
     * @param canManageChat           Required. If {@code true}, can view the event log, boost list, see hidden members
     *                                of a supergroup/channel, report spam, ignore slow mode, and send messages without
     *                                paying with Telegram Stars. This right is implied by any other admin right.
     * @param canDeleteMessages       Required. If {@code true}, can delete messages of other users.
     * @param canManageVideoChats     Required. If {@code true}, can manage video chats.
     * @param canRestrictMembers      Required. If {@code true}, can restrict/ban/unban members or view supergroup
     *                                stats.
     * @param canPromoteMembers       Required. If {@code true}, can appoint new admins (with a subset of own rights)
     *                                and demote admins appointed by themselves (directly or indirectly).
     * @param canChangeInfo           Required. If {@code true}, can change the chat title, photo, and other settings.
     * @param canInviteUsers          Required. If {@code true}, can invite new users.
     * @param canPostStories          Required. If {@code true}, can post stories in the chat.
     * @param canEditStories          Required. If {@code true}, can edit others’ stories, post stories on the chat’s
     *                                page, pin chat stories, and access the story archive.
     * @param canDeleteStories        Required. If {@code true}, can delete stories posted by other users.
     * @param canPostMessages         Optional. If {@code true}, can post messages in the channel, approve suggested
     *                                posts, and view channel statistics; channels only.
     * @param canEditMessages         Optional. If {@code true}, can edit messages of other users and pin messages;
     *                                channels only.
     * @param canPinMessages          Optional. If {@code true}, can pin messages; groups and supergroups only.
     * @param canManageTopics         Optional. If {@code true}, can create, rename, close and reopen forum topics;
     *                                supergroups only.
     * @param canManageDirectMessages Optional. If {@code true}, can manage the channel’s “direct messages” and reject
     *                                suggested posts; channels only.
     * @see <a href="https://core.telegram.org/bots/api#chatadministratorrights">
     * Telegram Bot API: ChatAdministratorRights</a>
     */
    public record ChatAdministratorRights(
            boolean isAnonymous,
            boolean canManageChat,
            boolean canDeleteMessages,
            boolean canManageVideoChats,
            boolean canRestrictMembers,
            boolean canPromoteMembers,
            boolean canChangeInfo,
            boolean canInviteUsers,
            boolean canPostStories,
            boolean canEditStories,
            boolean canDeleteStories,
            Boolean canPostMessages,
            Boolean canEditMessages,
            Boolean canPinMessages,
            Boolean canManageTopics,
            Boolean canManageDirectMessages
    ) {
    }

    /**
     * Create a plain text button.
     */
    public static KeyboardButton text(@Nonnull final String text) {
        return new KeyboardButton(text, null);
    }
}
