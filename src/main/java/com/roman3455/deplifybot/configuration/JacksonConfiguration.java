package com.roman3455.deplifybot.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Jackson configuration class for tuning JSON (de)serialization.
 *
 * <p>Configures the {@link com.fasterxml.jackson.databind.ObjectMapper} via
 * {@link org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer}.</p>
 *
 * <p>Configuration highlights:</p>
 * <ul>
 *   <li>
 *     {@link JavaTimeModule JavaTimeModule} — enables support for {@code java.time.*} types.
 *   </li>
 *   <li>
 *     {@link PropertyNamingStrategies#SNAKE_CASE SNAKE_CASE} — uses {@code snake_case} for property names.
 *   </li>
 *   <li>
 *     {@link JsonInclude.Include#NON_NULL NON_NULL} — omits {@code null}-valued properties from JSON.
 *   </li>
 *   <li>
 *     {@link DeserializationFeature#READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE
 *     READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE} — maps unknown {@code enum} values to the default constant (the
 *     one annotated with {@link com.fasterxml.jackson.annotation.JsonEnumDefaultValue}).
 *   </li>
 *   <li>
 *     {@link SerializationFeature#WRITE_DATES_AS_TIMESTAMPS WRITE_DATES_AS_TIMESTAMPS} (disabled) —
 *     serialize Java time types as ISO-8601 strings instead of numeric timestamps.
 *   </li>
 *   <li>
 *     {@link DeserializationFeature#FAIL_ON_UNKNOWN_PROPERTIES FAIL_ON_UNKNOWN_PROPERTIES} (disabled) —
 *     ignore unknown JSON properties during deserialization.
 *   </li>
 * </ul>
 */
@Configuration
public class JacksonConfiguration {

    /**
     * Customizer for {@link org.springframework.http.converter.json.Jackson2ObjectMapperBuilder}.
     *
     * @return a customizer that Spring Boot applies when creating the {@code ObjectMapper}.
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> builder
                .modulesToInstall(new JavaTimeModule())
                .propertyNamingStrategy(PropertyNamingStrategies.SNAKE_CASE)
                .serializationInclusion(JsonInclude.Include.NON_NULL)
                .featuresToEnable(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE)
                .featuresToDisable(
                        SerializationFeature.WRITE_DATES_AS_TIMESTAMPS,
                        DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES
                );
    }
}
