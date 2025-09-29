package com.roman3455.deplifybot.dto.telegram.nested;

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
