package com.springoffice.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springoffice.book.client.UserClient;
import com.springoffice.book.entity.Conversation;
import com.springoffice.book.mapper.ConversationMapper;
import com.springoffice.book.service.ConversationService;
import com.springoffice.global.util.DataResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("conversation-service")
public class ConversationServiceImpl implements ConversationService {
    @Resource
    private ConversationMapper conversationMapper;
    @Resource
    private UserClient userClient;

    @Override
    public DataResult<Conversation> createConversation(Conversation conversation) {
        if (conversationExists(conversation)) {
            return DataResult.error("Conversation创建失败，已经存在", conversation);
        }
        int resultValue = conversationMapper.insert(conversation);
        if (resultValue <= 0) {
            return DataResult.error("Conversation创建失败", conversation);
        }
        updateUserName(conversation);
        return DataResult.ok("Conversation创建成功", conversation);
    }

    @Override
    public DataResult<List<Conversation>> getConversationList(Integer userId) {
        LambdaQueryWrapper<Conversation> wrapperA = new LambdaQueryWrapper<>();
        wrapperA.eq(Conversation::getAId, userId);
        LambdaQueryWrapper<Conversation> wrapperB = new LambdaQueryWrapper<>();
        wrapperB.eq(Conversation::getBId, userId);
        List<Conversation> conversationList = conversationMapper.selectList(wrapperA);
        conversationList.addAll(conversationMapper.selectList(wrapperB));
        conversationList.forEach(this::updateUserName);
        return DataResult.ok("Conversation List查询成功", conversationList);
    }

    @Override
    public DataResult<Conversation> getConversation(Integer aId, Integer bId) {
        LambdaQueryWrapper<Conversation> wrapperA = new LambdaQueryWrapper<>();
        wrapperA.eq(Conversation::getAId, aId)
                .eq(Conversation::getBId, bId);
        List<Conversation> targets = conversationMapper.selectList(wrapperA);
        if (!targets.isEmpty()) {
            return DataResult.ok("Conversation查询成功", targets.get(0));
        }
        LambdaQueryWrapper<Conversation> wrapperB = new LambdaQueryWrapper<>();
        wrapperB.eq(Conversation::getAId, bId)
                .eq(Conversation::getBId, aId);
        targets.addAll(conversationMapper.selectList(wrapperB));
        if (targets.isEmpty()) {
            return DataResult.error("Conversation查询失败，ID:(" + aId + ", " + bId + ")之间未创建会话");
        }
        updateUserName(targets.get(0));
        return DataResult.ok("Conversation查询成功", targets.get(0));
    }

    @Override
    public DataResult<Conversation> getConversationById(Integer id) {
        Conversation conversation = conversationMapper.selectById(id);
        if (conversation == null) {
            return DataResult.error("Conversation查询失败，ID:" + id + "不存在");
        }
        updateUserName(conversation);
        return DataResult.ok("Conversation查询成功", conversation);
    }

    @Override
    public DataResult<Object> deleteConversation(Integer id) {
        int resultValue = conversationMapper.deleteById(id);
        if (resultValue <= 0) {
            return DataResult.error("会话删除失败");
        }
        return DataResult.ok("会话删除成功");
    }

    private boolean conversationExists(Conversation conversation) {
        LambdaQueryWrapper<Conversation> wrapperA = new LambdaQueryWrapper<>();
        wrapperA.eq(Conversation::getAId, conversation.getAId())
                .eq(Conversation::getBId, conversation.getBId());
        List<Conversation> target = conversationMapper.selectList(wrapperA);
        if (!target.isEmpty()) {
            return true;
        }
        LambdaQueryWrapper<Conversation> wrapperB = new LambdaQueryWrapper<>();
        wrapperB.eq(Conversation::getAId, conversation.getBId())
                .eq(Conversation::getBId, conversation.getAId());
        return !conversationMapper.selectList(wrapperB).isEmpty();
    }

    private void updateUserName(Conversation conversation) {
        conversation.setAName(userClient.getUserById(conversation.getAId()).unwrap().getName());
        conversation.setBName(userClient.getUserById(conversation.getBId()).unwrap().getName());
    }
}
