package com.roman3455.deplifybot.dto.telegram.markup;

import com.roman3455.deplifybot.configuration.JacksonConfiguration;
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
@DisplayName("ReplyKeyboardRemove JSON serialization")
public class ReplyKeyboardRemoveJsonTest {

    @Autowired
    private JacksonTester<ReplyKeyboardRemove> json;

    private static final String URL = "/fixtures/telegram/markup/reply_keyboard_remove/";

    @Test
    @DisplayName("Given ReplyKeyboardRemove, When serialized, Then JSON matches fixture")
    void replyKeyboardRemoveSerialization() throws Exception {
        ReplyKeyboardRemove given = new ReplyKeyboardRemove(true, false);
        var actual = json.write(given);

        then(actual).isEqualToJson(URL + "reply_keyboard_remove.json");
        then(actual).extractingJsonPathBooleanValue("$.remove_keyboard").isTrue();
        then(actual).doesNotHaveJsonPath("$.replyKeyboardRemove");
        then(actual).extractingJsonPathBooleanValue("$.selective").isFalse();
    }

    @Test
    @DisplayName("Given ReplyKeyboardRemove, When have false value, Then throw exception")
    void replyKeyboardRemoveWithFalseSelectiveFieldThrowException() {
        thenThrownBy(() -> new ReplyKeyboardRemove(false, false))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("Field 'removeKeyboard' must be true.");
    }
}
