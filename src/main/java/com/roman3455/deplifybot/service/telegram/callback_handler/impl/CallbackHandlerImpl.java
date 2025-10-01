package com.roman3455.deplifybot.service.telegram.callback_handler.impl;

import com.roman3455.deplifybot.dto.telegram.Update;
import com.roman3455.deplifybot.service.telegram.callback_handler.CallbackHandler;
import com.roman3455.deplifybot.service.telegram.callback_handler.callback.Callback;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CallbackHandlerImpl implements CallbackHandler {

    Map<String, Callback> callbacksMap;

    public CallbackHandlerImpl(List<Callback> callbacks) {
        this.callbacksMap = new HashMap<>();
        callbacks.forEach(callback -> callbacksMap.put(callback.getType().getName(), callback));
    }

    @Override
    public void handle(Update update) {
        var callbackData = update.callbackQuery().data();
        Callback callback = callbacksMap.get(callbackData);
        if (callback != null) {
            callback.apply(update);
        }
    }
}
