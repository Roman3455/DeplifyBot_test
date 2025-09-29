package com.roman3455.deplifybot.service.telegram.callback_handler;

import com.roman3455.deplifybot.dto.telegram.Update;

public interface CallbackHandler {

    void handle(Update update);
}
