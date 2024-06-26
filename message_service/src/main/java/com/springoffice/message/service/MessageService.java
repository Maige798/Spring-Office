package com.springoffice.message.service;

import com.springoffice.global.util.DataResult;
import com.springoffice.message.entity.Message;
import com.springoffice.message.entity.json.SendMessageJson;

import java.util.List;

public interface MessageService {
    DataResult<List<Message>> getMessageList(Integer userId);

    DataResult<Message> getMessageById(Integer id);

    DataResult<List<Message>> sendMessages(SendMessageJson json);

    DataResult<Object> deleteMessage(Integer id);
}
