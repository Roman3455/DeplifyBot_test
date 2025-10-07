package com.roman3455.deplifybot.service.telegram;

import com.roman3455.deplifybot.dto.telegram.inbound.Update;

public interface CommandDispatcher {

    void dispatch(Update update);
}
