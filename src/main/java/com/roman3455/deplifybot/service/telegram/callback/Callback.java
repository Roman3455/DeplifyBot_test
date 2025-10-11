package com.roman3455.deplifybot.service.telegram.callback;

import com.roman3455.deplifybot.dto.telegram.api.response.Update;

public interface Callback {

    void apply(Update update);

    CallbackType getType();
}
