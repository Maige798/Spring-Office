package com.springoffice.book.service.impl;

import com.springoffice.book.entity.Conversation;
import com.springoffice.book.entity.ConversationMessage;
import com.springoffice.book.entity.ConversationOrder;
import com.springoffice.book.mapper.ConversationMapper;
import com.springoffice.book.mapper.ConversationMessageMapper;
import com.springoffice.book.mapper.ConversationOrderMapper;
import com.springoffice.book.service.ConversationMessageService;
import com.springoffice.global.util.DataResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("conversation-message-service")
public class ConversationMessageServiceImpl implements ConversationMessageService {
    @Resource
    private ConversationMapper conversationMapper;
    @Resource
    private ConversationMessageMapper conversationMessageMapper;
    @Resource
    private ConversationOrderMapper conversationOrderMapper;

    @Override
    @Transactional
    public DataResult<ConversationMessage> sendMessage(ConversationMessage message) {
        Conversation conversation = conversationMapper.selectById(message.getConversationId());
        if (conversation == null) {
            return DataResult.error("Conversation Message发送失败，Conversation ID不存在", message);
        }
        if (!checkSender(message, conversation)) {
            return DataResult.error("Conversation Message发送失败，用户不匹配", message);
        }
        Integer nextOrder = getNextOrder(message.getConversationId());
        if (nextOrder.equals(-1)) {
            return DataResult.error("Conversation Message发送失败", message);
        }
        message.setOrderNumber(nextOrder);
        int resultValue = conversationMessageMapper.insert(message);
        if (resultValue <= 0) {
            return DataResult.error("Conversation Message发送失败", message);
        }
        return DataResult.ok("Conversation Message发送成功", message);
    }

    private boolean checkSender(ConversationMessage message, Conversation conversation) {
        return conversation.getAId().equals(message.getSenderId()) || conversation.getBId().equals(message.getSenderId());
    }

    private Integer getNextOrder(Integer conversationId) {
        ConversationOrder order = conversationOrderMapper.selectById(conversationId);
        if (order == null) {
            return -1;
        }
        order.setNextOrder(order.getNextOrder() + 1);
        conversationOrderMapper.updateById(order);
        return order.getNextOrder() - 1;
    }
}
