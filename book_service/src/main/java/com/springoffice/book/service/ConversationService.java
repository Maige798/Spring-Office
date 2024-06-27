package com.springoffice.book.service;

import com.springoffice.book.entity.Conversation;
import com.springoffice.global.util.DataResult;

import java.util.List;

public interface ConversationService {
    DataResult<Conversation> createConversation(Conversation conversation);

    DataResult<List<Conversation>> getConversationList(Integer userId);

    DataResult<Conversation> getConversation(Integer aId, Integer bId);

    DataResult<Conversation> getConversationById(Integer id);
}
