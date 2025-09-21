package com.roman3455.deplifybot.dto.telegram.markup;

import com.roman3455.deplifybot.configuration.JacksonConfiguration;
import com.roman3455.deplifybot.dto.telegram.markup.button.InlineKeyboardButton;
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
@DisplayName("InlineKeyboardMarkup JSON serialization")
public class InlineKeyboardMarkupJsonTest {

    @Autowired
    private JacksonTester<InlineKeyboardMarkup> json;

    private static final String URL = "/fixtures/telegram/markup/inline_keyboard_markup/";

    @Test
    @DisplayName("Given ForceReply, When serialized, Then JSON matches fixture")
    void inlineKeyboardMarkupSerialization() throws Exception {
        var given = new InlineKeyboardMarkup(List.of(
                List.of(
                        InlineKeyboardButton.url("Link", "https://example.com"),
                        InlineKeyboardButton.callbackData("Label", "Payload")
                )
        ));
        var actual = json.write(given);

        then(actual).isEqualToJson(URL + "inline_keyboard_markup.json");
    }

    @Test
    @DisplayName("Given inlineKeyboardMarkup, When InlineKeyboardButton is null, Then throw exception")
    void inlineKeyboardMarkupWithNullInlineKeyboardButtonThrowException() {
        thenThrownBy(() -> new InlineKeyboardMarkup(null))
                .isInstanceOf(NullPointerException.class)
                .hasMessageContaining("Field 'inlineKeyboard' must not be null.");
    }
}
