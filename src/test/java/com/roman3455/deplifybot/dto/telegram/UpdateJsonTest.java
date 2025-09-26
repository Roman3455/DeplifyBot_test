package com.roman3455.deplifybot.dto.telegram;

import com.roman3455.deplifybot.configuration.JacksonConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.Import;

import static org.assertj.core.api.BDDAssertions.then;

@JsonTest
@Import(JacksonConfiguration.class)
@DisplayName("Update JSON deserialization")
class UpdateJsonTest {

    @Autowired
    private JacksonTester<Update> json;

    private static final String URL = "/fixtures/telegram/update/";
    private static final long UPDATE_ID = 1001L;
    private static final long MESSAGE_ID = 2001L;
    private static final long CHAT_ID = 3001L;
    private static final long FROM_ID = 4001L;

    @Test
    @DisplayName("Given 'message' update, When deserialized, Then core fields are populated and others are NULL")
    void messageUpdateDeserialization() throws Exception {
        Update given = json.readObject(URL + "update_message.json");

        then(given).isNotNull();
        then(given.updateId()).isEqualTo(UPDATE_ID);
        then(given.message()).isNotNull();
        then(given.message().messageId()).isEqualTo(MESSAGE_ID);
        then(given.message().date()).isNotNull();
        then(given.message().chat()).isNotNull();
        then(given.message().chat().id()).isEqualTo(CHAT_ID);
        then(given.message().chat().type()).isEqualTo("private");
        then(given.message().text()).isEqualTo("Test message");
        then(given.callbackQuery()).isNull();
        then(given.myChatMember()).isNull();
    }


    @Test
    @DisplayName("Given 'callback_query' update, When deserialized, Then core fields are populated and others are null")
    void callbackQueryUpdateDeserialization() throws Exception {
        Update given = json.readObject(URL + "update_callback_query.json");

        then(given).isNotNull();
        then(given.updateId()).isEqualTo(UPDATE_ID);
        then(given.callbackQuery()).isNotNull();
        then(given.callbackQuery().Id()).isEqualTo("cbq-1");
        then(given.callbackQuery().from()).isNotNull();
        then(given.callbackQuery().from().id()).isEqualTo(FROM_ID);
        then(given.callbackQuery().from().isBot()).isFalse();
        then(given.callbackQuery().from().firstName()).isEqualTo("Alice");
        then(given.callbackQuery().data()).isEqualTo("PING");
        then(given.message()).isNull();
        then(given.myChatMember()).isNull();
    }

    @Test
    @DisplayName("Given 'my_chat_member' update, When deserialized, Then core fields are populated and others are null")
    void myChatMemberUpdateDeserialization() throws Exception {
        Update given = json.readObject(URL + "update_my_chat_member.json");

        then(given).isNotNull();
        then(given.updateId()).isEqualTo(UPDATE_ID);
        then(given.myChatMember()).isNotNull();
        then(given.myChatMember().chat()).isNotNull();
        then(given.myChatMember().chat().id()).isEqualTo(CHAT_ID);
        then(given.myChatMember().chat().type()).isEqualTo("supergroup");
        then(given.myChatMember().from()).isNotNull();
        then(given.myChatMember().from().id()).isEqualTo(FROM_ID);
        then(given.myChatMember().oldChatMember().status()).isEqualTo("member");
        then(given.myChatMember().newChatMember().status()).isEqualTo("kicked");
        then(given.myChatMember().date()).isNotNull();
        then(given.message()).isNull();
        then(given.callbackQuery()).isNull();
    }

    @Test
    @DisplayName("Given update, When deserialized unknown fields, Then ignored during deserialization")
    void unknownUpdateDeserialization() throws Exception {
        Update given = json.readObject(URL + "update_with_unknowns.json");

        then(given).isNotNull();
        then(given.updateId()).isEqualTo(UPDATE_ID);
        then(given.message()).isNotNull();
        then(given.message().messageId()).isEqualTo(MESSAGE_ID);
    }
}
