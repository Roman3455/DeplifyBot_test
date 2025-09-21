package com.roman3455.deplifybot.dto.telegram;

import com.roman3455.deplifybot.configuration.JacksonConfiguration;
import com.roman3455.deplifybot.dto.telegram.markup.ReplyKeyboardRemove;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

@JsonTest
@Import(JacksonConfiguration.class)
@DisplayName("SendMessage JSON serialization")
public class SendMessageJsonTest {

    @Autowired
    private JacksonTester<SendMessage> json;

    private static final int MAX_TEXT_LENGTH = SendMessage.MAX_TEXT_LENGTH + 1;
    private static final int CHAT_ID = 123456789;
    private static final int MESSAGE_THREAD_ID = 42;

    @Test
    @DisplayName("Given SendMessage with min payload, When serialized, Then JSON matches fixture")
    void sendMessageSerializationWithMinPayload() throws Exception {
        var given = new SendMessage(CHAT_ID, null, "Test text", null, null, null);
        var actual = json.write(given);

        then(actual).extractingJsonPathNumberValue("$.chat_id").isEqualTo(given.chatId());
        then(actual).extractingJsonPathStringValue("$.text").isEqualTo(given.text());
        then(actual).doesNotHaveJsonPath("$.message_thread_id");
        then(actual).doesNotHaveJsonPath("$.parse_mode");
        then(actual).doesNotHaveJsonPath("$.disable_notification");
        then(actual).doesNotHaveJsonPath("$.reply_markup");
    }

    @Test
    @DisplayName("Given SendMessage with full payload, When serialized, Then JSON matches fixture")
    void sendMessageSerializationWithFullPayload() throws Exception {
        var given = new SendMessage(
                "1613511531",
                (long) MESSAGE_THREAD_ID,
                "Test text",
                "MarkdownV2",
                true,
                ReplyKeyboardRemove.allUsers()
        );
        var actual = json.write(given);

        then(actual).extractingJsonPathStringValue("$.chat_id").isEqualTo(given.chatId());
        then(actual).doesNotHaveJsonPath("$.chatId");
        then(actual).extractingJsonPathNumberValue("$.message_thread_id").isEqualTo(MESSAGE_THREAD_ID);
        then(actual).doesNotHaveJsonPath("$.messageThreadId");
        then(actual).extractingJsonPathStringValue("$.text").isEqualTo(given.text());
        then(actual).extractingJsonPathStringValue("$.parse_mode").isEqualTo(given.parseMode());
        then(actual).doesNotHaveJsonPath("$.parseMode");
        then(actual).extractingJsonPathBooleanValue("$.disable_notification").isTrue();
        then(actual).doesNotHaveJsonPath("$.disableNotification");
        then(actual).hasJsonPathValue("$.reply_markup");
        then(actual).doesNotHaveJsonPath("$.replyMarkup");
        then(actual).extractingJsonPathBooleanValue("$.reply_markup.remove_keyboard").isTrue();
    }

    @Test
    @DisplayName("Given SendMessage, When chatId field is NULL, Then throw exception")
    void sendMessageWithNullChatIdFieldThrowException() {
        thenThrownBy(() -> new SendMessage(null, null, "ok", null, null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Field 'chatId' must not be null.");
    }

    @Test
    @DisplayName("Given SendMessage, When text field is NULL, Then throw exception")
    void sendMessageWithNullTextFieldThrowException() {
        thenThrownBy(() -> new SendMessage(1L, null, null, null, null, null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Field 'text' must not be null.");
    }

    @Test
    @DisplayName("Given SendMessage, When text out length boundaries, Then throw exception")
    void sendMessageTextLengthBoundariesThrowException() {
        thenThrownBy(() -> new SendMessage(1L, null, "", null, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Allowed 'text' length is 1 to 4096 characters.");

        thenThrownBy(() -> new SendMessage(1L, null, "x".repeat(MAX_TEXT_LENGTH + 1), null, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Allowed 'text' length is 1 to 4096 characters.");
    }
}
