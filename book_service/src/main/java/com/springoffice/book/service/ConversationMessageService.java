package com.springoffice.book.service;

import com.springoffice.book.entity.ConversationMessage;
import com.springoffice.global.util.DataResult;

public interface ConversationMessageService {
    DataResult<ConversationMessage> sendMessage(ConversationMessage message);
}
