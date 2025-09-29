//package com.roman3455.deplifybot.dto.telegram.markup.button;
//
//import com.roman3455.deplifybot.configuration.JacksonConfiguration;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.params.ParameterizedTest;
//import org.junit.jupiter.params.provider.Arguments;
//import org.junit.jupiter.params.provider.MethodSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.json.JsonTest;
//import org.springframework.boot.test.json.JacksonTester;
//import org.springframework.context.annotation.Import;
//
//import java.util.stream.Stream;
//
//import static org.assertj.core.api.BDDAssertions.then;
//import static org.assertj.core.api.BDDAssertions.thenThrownBy;
//
//@JsonTest
//@Import(JacksonConfiguration.class)
//@DisplayName("InlineKeyboardButton JSON serialization")
//final class InlineKeyboardButtonJsonTest {
//
//    @Autowired
//    private JacksonTester<InlineKeyboardButton> json;
//
//    private static final String URL = "/fixtures/telegram/markup/button/inline_keyboard_button/";
//    private static final int OUT_OF_BOUNDS_TEXT_LENGTH = InlineKeyboardButton.CopyTextButton.MAX_TEXT_LENGTH + 1;
//    private static final int OUT_OF_BOUNDS_TEXT_BYTE_LENGTH = InlineKeyboardButton.MAX_CALLBACK_DATA_LENGTH + 1;
//    private static final int OUT_OF_BOUNDS_EMOJI_BYTE_LENGTH = 17;
//
//    @ParameterizedTest(name = "{index} -> {0}")
//    @MethodSource("serializationCases")
//    @DisplayName("Given InlineKeyboardButton, When serialized, Then JSON matches fixture")
//    void inlineKeyboardButtonSerialization(
//            final String caseTitle,
//            final InlineKeyboardButton given,
//            final String fixture
//    ) throws Exception {
//        var actual = json.write(given);
//
//        then(actual).isEqualToJson(URL + fixture);
//        then(actual).extractingJsonPathStringValue("$.text").isEqualTo(given.text());
//
//        if (given.callbackData() != null) {
//            then(actual).doesNotHaveJsonPath("$.url");
//            then(actual).extractingJsonPathStringValue("$.callback_data")
//                    .isEqualTo(given.callbackData());
//            then(actual).doesNotHaveJsonPath("$.callbackData");
//            then(actual).doesNotHaveJsonPath("$.copy_text");
//        }
//
//        if (given.copyText() != null) {
//            then(actual).doesNotHaveJsonPath("$.url");
//            then(actual).doesNotHaveJsonPath("$.callback_data");
//            then(actual).extractingJsonPathStringValue("$.copy_text.text")
//                    .isEqualTo(given.copyText().text());
//            then(actual).doesNotHaveJsonPath("$.copyText");
//        }
//
//        if (given.url() != null) {
//            then(actual).extractingJsonPathStringValue("$.url")
//                    .isEqualTo(given.url());
//            then(actual).doesNotHaveJsonPath("$.callback_data");
//            then(actual).doesNotHaveJsonPath("$.copy_text");
//        }
//    }
//
//    private static Stream<Arguments> serializationCases() {
//        return Stream.of(
//                Arguments.of(
//                        "CallbackData",
//                        InlineKeyboardButton.callbackData("CallbackData button", "Received data"),
//                        "inline_button_callback_data.json"
//                ),
//                Arguments.of(
//                        "CopyText",
//                        InlineKeyboardButton.copyText("CopyText button", "Text to copy"),
//                        "inline_button_copy_text.json"
//                ),
//                Arguments.of(
//                        "URL",
//                        InlineKeyboardButton.url("URL button", "https://example.com"),
//                        "inline_button_url.json"
//                )
//        );
//    }
//
//    @Test
//    @DisplayName("Given InlineKeyboardButton, When text field is NULL, Then throw exception")
//    void inlineKeyboardButtonWithNullTextFieldThrowException() {
//        thenThrownBy(() -> InlineKeyboardButton.url(null, "https://ex.com"))
//                .isInstanceOf(NullPointerException.class)
//                .hasMessageContaining("Field 'text' must not be null.");
//    }
//
//    @Test
//    @DisplayName("Given CopyTextButton, When text field is NULL, Then throw exception")
//    void copyTextButtonWithNullTextFieldThrowException() {
//        thenThrownBy(() -> new InlineKeyboardButton.CopyTextButton(null))
//                .isInstanceOf(NullPointerException.class)
//                .hasMessageContaining("Field 'text' must not be null.");
//    }
//
//    @Test
//    @DisplayName("Given InlineKeyboardButton, When have extra optional field, Then throw exception")
//    void inlineKeyboardButtonWithExtraFieldThrowException() {
//        thenThrownBy(() -> new InlineKeyboardButton("text", "https://", "data", null))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Exactly one of 'url', 'callbackData', 'copyText' must be present.");
//    }
//
//    @Test
//    @DisplayName("Given CopyTextButton, When text out length boundaries, Then throw exception")
//    void copyTextButtonTextLengthBoundariesThrowException() {
//        thenThrownBy(() -> InlineKeyboardButton.copyText("Button", ""))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Allowed 'text' length is 1 to 256 characters.");
//
//        thenThrownBy(() -> InlineKeyboardButton.copyText(
//                "Button", "x".repeat(OUT_OF_BOUNDS_TEXT_LENGTH)
//        ))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Allowed 'text' length is 1 to 256 characters.");
//    }
//
//    @Test
//    @DisplayName("Given InlineKeyboardButton, When callbackData out length boundaries, Then throw exception")
//    void inlineKeyboardButtonCallbackDataLengthBoundariesThrowException() {
//        thenThrownBy(() -> InlineKeyboardButton.callbackData("Button", ""))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Field 'callbackData' must be between 1 and 64 bytes in UTF-8.");
//
//        thenThrownBy(() -> InlineKeyboardButton.callbackData(
//                "Button", "x".repeat(OUT_OF_BOUNDS_TEXT_BYTE_LENGTH)
//        ))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Field 'callbackData' must be between 1 and 64 bytes in UTF-8.");
//
//        thenThrownBy(() -> InlineKeyboardButton.callbackData(
//                "Button", "👍".repeat(OUT_OF_BOUNDS_EMOJI_BYTE_LENGTH)
//        ))
//                .isInstanceOf(IllegalArgumentException.class)
//                .hasMessageContaining("Field 'callbackData' must be between 1 and 64 bytes in UTF-8.");
//    }
//}
