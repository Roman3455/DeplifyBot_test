package com.roman3455.deplifybot.service.telegram.message.impl;

import com.roman3455.deplifybot.dto.telegram.api.response.Update;
import com.roman3455.deplifybot.service.telegram.message.MessageDispatcher;
import org.springframework.stereotype.Service;

@Service
public class MessageDispatcherImpl implements MessageDispatcher {
    @Override
    public void dispatch(final Update update) {

    }
}
