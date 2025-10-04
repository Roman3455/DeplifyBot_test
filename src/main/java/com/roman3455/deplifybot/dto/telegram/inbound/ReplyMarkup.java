package com.roman3455.deplifybot.dto.telegram.inbound;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.DEDUCTION)
public interface ReplyMarkup {
}
