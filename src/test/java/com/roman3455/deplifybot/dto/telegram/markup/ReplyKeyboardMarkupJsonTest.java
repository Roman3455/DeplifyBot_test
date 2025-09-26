package com.roman3455.deplifybot.dto.telegram.markup;

import com.roman3455.deplifybot.configuration.JacksonConfiguration;
import com.roman3455.deplifybot.dto.telegram.markup.button.KeyboardButton;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.context.annotation.Import;

import java.util.List;

import static org.assertj.core.api.BDDAssertions.then;
import static org.assertj.core.api.BDDAssertions.thenThrownBy;

@JsonTest
@Import(JacksonConfiguration.class)
@DisplayName("ReplyKeyboardMarkup JSON serialization")
class ReplyKeyboardMarkupJsonTest {

    @Autowired
    private JacksonTester<ReplyKeyboardMarkup> json;

    private static final String URL = "/fixtures/telegram/markup/reply_keyboard_markup/";
    private static final int OUT_OF_BOUNDS_PLACEHOLDER = ReplyKeyboardMarkup.MAX_PLACEHOLDER_LENGTH + 1;

    @Test
    @DisplayName("Given ReplyKeyboardMarkup, When serialized, Then JSON matches fixture")
    void replyKeyboardMarkupSerialization() throws Exception {
        ReplyKeyboardMarkup given = new ReplyKeyboardMarkup(
                List.of(List.of(KeyboardButton.text("Label"))),
                true,
                true,
                true,
                "Placeholder",
                false
        );
        var actual = json.write(given);

        then(actual).isEqualToJson(URL + "reply_keyboard_markup.json");
        then(actual).hasJsonPathArrayValue("$.keyboard");
        then(actual).extractingJsonPathArrayValue("$.keyboard").hasSize(1);
        then(actual).extractingJsonPathArrayValue("$.keyboard[0]").hasSize(1);
        then(actual).extractingJsonPathStringValue("$.keyboard[0][0].text")
                .isEqualTo("Label");
        then(actual).extractingJsonPathBooleanValue("$.is_persistent").isTrue();
        then(actual).doesNotHaveJsonPath("$.isPersistent");
        then(actual).extractingJsonPathBooleanValue("$.resize_keyboard").isTrue();
        then(actual).doesNotHaveJsonPath("$.resizeKeyboard");
        then(actual).extractingJsonPathBooleanValue("$.one_time_keyboard").isTrue();
        then(actual).doesNotHaveJsonPath("$.oneTimeKeyboard");
        then(actual).extractingJsonPathStringValue("$.input_field_placeholder")
                .isEqualTo("Placeholder");
        then(actual).doesNotHaveJsonPath("$.inputFieldPlaceholder");
        then(actual).extractingJsonPathBooleanValue("$.selective").isFalse();
    }

    @Test
    @DisplayName("Given ReplyKeyboardMarkup, When placeholder out length boundaries, Then throw exception")
    void replyKeyboardMarkupPlaceholderFieldLengthBoundariesThrowException() {
        thenThrownBy(() -> new ReplyKeyboardMarkup(
                List.of(List.of(KeyboardButton.text("Label"))),
                null,
                null,
                null,
                "x".repeat(OUT_OF_BOUNDS_PLACEHOLDER),
                null
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Allowed 'inputFieldPlaceholder' length is 1 to 64 characters.");

        thenThrownBy(() -> new ReplyKeyboardMarkup(
                List.of(List.of(KeyboardButton.text("Label"))), null, null, null, "", null
        ))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Allowed 'inputFieldPlaceholder' length is 1 to 64 characters.");
    }

    @Test
    @DisplayName("Given ReplyKeyboardMarkup, When keyboard field is NULL, Then throw exception")
    void replyKeyboardMarkupWithNullKeyboardFieldThrowException() {
        thenThrownBy(() -> new ReplyKeyboardMarkup(null, null, null, null, "", null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Field 'keyboard' must not be null.");
    }
}
