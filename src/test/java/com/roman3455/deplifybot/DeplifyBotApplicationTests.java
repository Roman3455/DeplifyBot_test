package com.roman3455.deplifybot;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class DeplifyBotApplicationTests {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void contextLoads() {
        assertThat(applicationContext).isNotNull();
        assertThat(objectMapper).isNotNull();
    }

    @Test
    void jacksonIsConfiguredAsExpected() {
        assertThat(objectMapper).isNotNull();
        assertThat(objectMapper.getPropertyNamingStrategy())
                .isInstanceOf(PropertyNamingStrategies.SnakeCaseStrategy.class);
        var inclusion = objectMapper.getSerializationConfig().getDefaultPropertyInclusion();
        assertThat(inclusion.getValueInclusion())
                .isEqualTo(JsonInclude.Include.NON_NULL);
        assertThat(objectMapper.isEnabled(DeserializationFeature.READ_UNKNOWN_ENUM_VALUES_USING_DEFAULT_VALUE))
                .isTrue();
        assertThat(objectMapper.isEnabled(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS))
                .isFalse();
        assertThat(objectMapper.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES))
                .isFalse();
    }

}
