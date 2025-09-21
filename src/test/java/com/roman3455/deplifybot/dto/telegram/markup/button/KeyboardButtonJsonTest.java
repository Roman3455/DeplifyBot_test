package com.roman3455.deplifybot.dto.telegram.markup.button;

import com.roman3455.deplifybot.configuration.JacksonConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.Import;

import java.util.stream.Stream;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

@JsonTest
@Import(JacksonConfiguration.class)
@DisplayName("KeyboardButton JSON serialization")
public final class KeyboardButtonJsonTest {

    @Autowired
    private JacksonTester<KeyboardButton> json;

    private static final String URL = "/fixtures/telegram/markup/button/keyboard_button/";
    private static final long REQUEST_ID = 123L;

    @ParameterizedTest(name = "{index} -> {0}")
    @MethodSource("serializationCases")
    @DisplayName("Given KeyboardButton, When serialized, Then JSON matches fixture")
    void keyboardButtonSerialization(
            final String caseTitle,
            final KeyboardButton given,
            final String fixture
    ) throws Exception {
        var actual = json.write(given);

        then(actual).isEqualToJson(URL + fixture);
        then(actual).extractingJsonPathStringValue("$.text").isEqualTo(given.text());

        if (given.requestChat() == null) {
            then(actual).doesNotHaveJsonPath("$.request_chat");
            then(actual).doesNotHaveJsonPath("$.requestChat");
        }

        if (given.requestChat() != null) {
            var rc = given.requestChat();
            then(actual)
                    .extractingJsonPathNumberValue("$.request_chat.request_id")
                    .isEqualTo(rc.requestId().intValue());
            then(actual)
                    .extractingJsonPathBooleanValue("$.request_chat.chat_is_channel")
                    .isEqualTo(rc.chatIsChannel());

            if (rc.userAdministratorRights() != null) {
                then(actual)
                        .extractingJsonPathBooleanValue("$.request_chat.user_administrator_rights.is_anonymous")
                        .isEqualTo(rc.userAdministratorRights().isAnonymous());
                then(actual)
                        .extractingJsonPathBooleanValue("$.request_chat.user_administrator_rights.can_manage_chat")
                        .isEqualTo(rc.userAdministratorRights().canManageChat());
                then(actual)
                        .doesNotHaveJsonPath("$.request_chat.user_administrator_rights.can_post_messages");
            }

            if (rc.userAdministratorRights() == null) {
                then(actual).doesNotHaveJsonPath("$.request_chat.user_administrator_rights");
            }

            if (rc.botAdministratorRights() != null) {
                then(actual)
                        .extractingJsonPathBooleanValue("$.request_chat.bot_administrator_rights.can_delete_messages")
                        .isEqualTo(rc.botAdministratorRights().canDeleteMessages());
            }

            if (rc.botAdministratorRights() == null) {
                then(actual).doesNotHaveJsonPath("$.request_chat.bot_administrator_rights");
            }

            if (rc.botIsMember() != null) {
                then(actual)
                        .extractingJsonPathBooleanValue("$.request_chat.bot_is_member")
                        .isEqualTo(rc.botIsMember());
            }

            if (rc.botIsMember() == null) {
                then(actual).doesNotHaveJsonPath("$.request_chat.bot_is_member");
            }

            if (rc.requestTitle() != null) {
                then(actual)
                        .extractingJsonPathBooleanValue("$.request_chat.request_title")
                        .isEqualTo(rc.requestTitle());
            }

            if (rc.requestTitle() == null) {
                then(actual).doesNotHaveJsonPath("$.request_chat.request_title");
            }
        }
    }

    private static Stream<Arguments> serializationCases() {
        return Stream.of(
                Arguments.of(
                        "Text-only",
                        KeyboardButton.text("Just a text button"),
                        "keyboard_button_text.json"
                ),
                Arguments.of(
                        "RequestChat minimal",
                        new KeyboardButton(
                                "Pick a chat",
                                new KeyboardButton.KeyboardButtonRequestChat(
                                        REQUEST_ID,
                                        true,
                                        null,
                                        null,
                                        null,
                                        null
                                )
                        ),
                        "keyboard_button_request_chat_min.json"
                ),
                Arguments.of(
                        "RequestChat with rights",
                        new KeyboardButton(
                                "Pick a group",
                                new KeyboardButton.KeyboardButtonRequestChat(
                                        REQUEST_ID,
                                        false,
                                        new KeyboardButton.ChatAdministratorRights(
                                                true,
                                                true,
                                                true,
                                                true,
                                                true,
                                                true,
                                                true,
                                                true,
                                                true,
                                                true,
                                                true,
                                                null,
                                                null,
                                                true,
                                                true,
                                                null
                                        ),
                                        new KeyboardButton.ChatAdministratorRights(
                                                false,
                                                true,
                                                false,
                                                true,
                                                false,
                                                false,
                                                false,
                                                true,
                                                false,
                                                false,
                                                false,
                                                null,
                                                null,
                                                true,
                                                null,
                                                null
                                        ),
                                        Boolean.TRUE,
                                        Boolean.FALSE
                                )
                        ),
                        "keyboard_button_request_chat_rights.json"
                )
        );
    }

    @Test
    @DisplayName("Given KeyboardButton, When text field is NULL, Then throw exception")
    void keyboardButtonWithNullTextThrowException() {
        thenThrownBy(() -> KeyboardButton.text(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Field 'text' must not be null.");
    }

    @Test
    @DisplayName("Given KeyboardButtonRequestChat, When request_id field is NULL, Then throw exception")
    void requestChatWithNullRequestIdThrowException() {
        thenThrownBy(() -> new KeyboardButton.KeyboardButtonRequestChat(
                null, true, null, null, null, null)
        )
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Field 'request_id' must not be null.");
    }
}
