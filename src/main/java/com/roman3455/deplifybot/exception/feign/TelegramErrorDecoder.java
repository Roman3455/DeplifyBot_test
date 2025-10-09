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

public record TelegramErrorDecoder(
        ObjectMapper mapper
) implements ErrorDecoder {

    private static final Logger LOG = LoggerFactory.getLogger(TelegramErrorDecoder.class);

    private static final int BAD_REQUEST = 400;
    private static final int UNAUTHORIZED = 401;
    private static final int FORBIDDEN = 403;
    private static final int NOT_FOUND = 404;
    private static final int NOT_ACCEPTABLE = 406;
    private static final int PAYLOAD_TOO_LARGE = 413;
    private static final int ENHANCE_YOUR_CALM = 420;
    private static final int TOO_MANY_REQUESTS = 429;
    private static final int INTERNAL_SERVER_ERROR = 500;
    private static final int BAD_GATEWAY = 502;
    private static final int SERVICE_UNAVAILABLE = 503;
    private static final int GATEWAY_TIMEOUT = 504;

    @Override
    public Exception decode(final String methodKey, final Response response) {
        if (response.body() == null) {
            return new RuntimeException("Telegram returned empty error body, status: " + response.status());
        }

        try (InputStream inputStream = response.body().asInputStream()) {
            TelegramResponseMessage<?> exception = mapper.readValue(inputStream, TelegramResponseMessage.class);
            LOG.warn("Feign method [{}] failed. Telegram API error [{}]: {}",
                    methodKey, response.status(), exception.description());
            return switch (response.status()) {
                case BAD_REQUEST -> new TelegramBadRequestException(exception.description());
                case UNAUTHORIZED -> new TelegramUnauthorizedException(exception.description());
                case FORBIDDEN -> new TelegramForbiddenException(exception.description());
                case NOT_FOUND -> new TelegramNotFoundException(exception.description());
                case NOT_ACCEPTABLE -> new TelegramNotAcceptableException(exception.description());
                case PAYLOAD_TOO_LARGE -> new TelegramPayloadTooLargeException(exception.description());
                case ENHANCE_YOUR_CALM, TOO_MANY_REQUESTS -> {
                    long retryAfter = Optional.ofNullable(exception.parameters())
                            .map(TelegramResponseMessage.ResponseParameters::retryAfter)
                            .orElse(0);
                    yield new TelegramTooManyRequestsException(exception.description(), retryAfter);
                }
                case INTERNAL_SERVER_ERROR, BAD_GATEWAY, SERVICE_UNAVAILABLE, GATEWAY_TIMEOUT ->
                        new TelegramServerException(exception.description());
                default -> new RuntimeException("Unexpected Telegram exception (" + response.status() + "): "
                        + exception.description());
            };
        } catch (IOException e) {
            LOG.error("Failed to parse Telegram error response", e);
            return FeignException.errorStatus(methodKey, response);
        }
    }
}
