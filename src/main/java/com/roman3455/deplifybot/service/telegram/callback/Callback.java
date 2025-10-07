package com.roman3455.deplifybot.service.telegram.callback;

import com.roman3455.deplifybot.dto.telegram.inbound.Update;

public interface Callback {

    void apply(Update update);

    CallbackType getType();
}
