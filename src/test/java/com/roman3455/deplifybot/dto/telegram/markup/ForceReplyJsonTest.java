package com.roman3455.deplifybot.dto.telegram.markup;

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
@DisplayName("ForceReply JSON serialization")
public final class ForceReplyJsonTest {

    @Autowired
    private JacksonTester<ForceReply> json;

    private static final String URL = "/fixtures/telegram/markup/force_reply/";
    private static final int OUT_OF_BOUNDS_PLACEHOLDER = ForceReply.MAX_PLACEHOLDER_LENGTH + 1;

    @ParameterizedTest(name = "{index} -> {0}")
    @MethodSource("serializationCases")
    @DisplayName("Given ForceReply, When serialized, Then JSON matches fixture")
    void forceReplySerialization(
            final String caseTitle,
            final ForceReply given,
            final String fixture
    ) throws Exception {
        var actual = json.write(given);

        then(actual).isEqualToJson(URL + fixture);
        then(actual).extractingJsonPathBooleanValue("$.force_reply").isTrue();

        if (given.inputFieldPlaceholder() != null) {
            then(actual).extractingJsonPathStringValue("$.input_field_placeholder")
                    .isEqualTo(given.inputFieldPlaceholder());
            then(actual).doesNotHaveJsonPath("$.inputFieldPlaceholder");
        }

        if (given.inputFieldPlaceholder() == null) {
            then(actual).doesNotHaveJsonPath("$.input_field_placeholder");
        }

        if (given.selective() != null) {
            then(actual).extractingJsonPathBooleanValue("$.selective")
                    .isEqualTo(given.selective());
        }

        if (given.selective() == null) {
            then(actual).doesNotHaveJsonPath("$.selective");
        }
    }

    private static Stream<Arguments> serializationCases() {
        return Stream.of(
                Arguments.of(
                        "Minimal",
                        ForceReply.reply(),
                        "force_reply_min.json"
                ),
                Arguments.of(
                        "With placeholder",
                        ForceReply.placeholder("Reply here…"),
                        "force_reply_with_placeholder.json"
                ),
                Arguments.of(
                        "With selective",
                        new ForceReply(true, null, Boolean.TRUE),
                        "force_reply_selective.json"
                )
        );
    }

    @Test
    @DisplayName("Given forceReply, When have false value, Then throw exception")
    void forceReplyFalseValueThrowException() {
        thenThrownBy(() -> new ForceReply(false, null, null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Field 'force_reply' must be true.");
    }

    @Test
    @DisplayName("Given placeholder, When have out length boundaries, Then throw exception")
    void inputFieldPlaceholderLengthBoundariesThrowException() {
        thenThrownBy(() -> new ForceReply(true, "", null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Allowed 'inputFieldPlaceholder' length is 1 to 64 characters.");

        thenThrownBy(() -> new ForceReply(true, "x".repeat(OUT_OF_BOUNDS_PLACEHOLDER), null))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Allowed 'inputFieldPlaceholder' length is 1 to 64 characters.");
    }
}
