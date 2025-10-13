package com.roman3455.deplifybot.service.telegram.message;

import com.roman3455.deplifybot.dto.telegram.api.response.Update;

public interface MessageDispatcher {
    void dispatch(Update update);
}
