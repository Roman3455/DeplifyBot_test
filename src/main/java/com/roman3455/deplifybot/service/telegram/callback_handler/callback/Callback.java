package com.roman3455.deplifybot.service.telegram.callback_handler.callback;

import com.roman3455.deplifybot.dto.telegram.inbound.Update;
import com.roman3455.deplifybot.service.telegram.callback_handler.CallbackType;

public interface Callback {

    void apply(Update update);

    CallbackType getType();
}
