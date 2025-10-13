package com.roman3455.deplifybot.service.telegram.callback.impl;

import com.roman3455.deplifybot.dto.telegram.api.response.Update;
import com.roman3455.deplifybot.service.telegram.callback.Callback;
import com.roman3455.deplifybot.service.telegram.callback.CallbackDispatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public final class CallbackDispatcherImpl implements CallbackDispatcher {

    private static final Logger LOG = LoggerFactory.getLogger(CallbackDispatcherImpl.class);
    private final Map<String, Callback> callbacksMap;

    public CallbackDispatcherImpl(final List<Callback> callbacks) {
        this.callbacksMap = new HashMap<>();
        callbacks.forEach(callback -> callbacksMap.put(callback.getType().getName(), callback));
    }

    @Override
    public void dispatch(final Update update) {
        var callbackData = update.callbackQuery().data();
        Callback callback = callbacksMap.get(callbackData);
        if (callback != null) {
            LOG.info("Found callback {}", callbackData);
            callback.apply(update);
        }
    }
}
