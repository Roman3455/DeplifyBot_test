package com.roman3455.deplifybot.exception.feign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.roman3455.deplifybot.dto.telegram.inbound.TelegramResponseMessage;
import com.roman3455.deplifybot.exception.feign.telegram.TelegramBadRequestException;
import com.roman3455.deplifybot.exception.feign.telegram.TelegramForbiddenException;
import com.roman3455.deplifybot.exception.feign.telegram.TelegramNotAcceptableException;
import com.roman3455.deplifybot.exception.feign.telegram.TelegramNotFoundException;
import com.roman3455.deplifybot.exception.feign.telegram.TelegramPayloadTooLargeException;
import com.roman3455.deplifybot.exception.feign.telegram.TelegramServerException;
import com.roman3455.deplifybot.exception.feign.telegram.TelegramTooManyRequestsException;
import com.roman3455.deplifybot.exception.feign.telegram.TelegramUnauthorizedException;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class TelegramErrorDecoder implements ErrorDecoder {

    private static final Logger LOG = LoggerFactory.getLogger(TelegramErrorDecoder.class);
    private final ObjectMapper mapper;

    public TelegramErrorDecoder(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public Exception decode(String methodKey, Response response) {
        if(response.body() == null) {
            return new RuntimeException("Telegram returned empty error body, status: " + response.status());
        }

        try(InputStream inputStream = response.body().asInputStream()) {
            TelegramResponseMessage<?> exception = mapper.readValue(inputStream, TelegramResponseMessage.class);
            LOG.warn("Feign method [{}] failed. Telegram API error [{}]: {}",
                    methodKey, response.status(), exception.description());
            return switch (response.status()) {
                case 400 -> new TelegramBadRequestException(exception.description());
                case 401 -> new TelegramUnauthorizedException(exception.description());
                case 403 -> new TelegramForbiddenException(exception.description());
                case 404 -> new TelegramNotFoundException(exception.description());
                case 406 -> new TelegramNotAcceptableException(exception.description());
                case 413 -> new TelegramPayloadTooLargeException(exception.description());
                case 420, 429 -> {
                    long retryAfter = Optional.ofNullable(exception.parameters())
                            .map(TelegramResponseMessage.ResponseParameters::retryAfter)
                            .orElse(0);
                    yield new TelegramTooManyRequestsException(exception.description(), retryAfter);
                }
                case 500, 502, 503, 504 -> new TelegramServerException(exception.description());
                default -> new RuntimeException("Unexpected Telegram exception (" + response.status() + "): "
                        + exception.description());
            };
        } catch (IOException e) {
            LOG.error("Failed to parse Telegram error response", e);
            return FeignException.errorStatus(methodKey, response);
        }
    }
}
