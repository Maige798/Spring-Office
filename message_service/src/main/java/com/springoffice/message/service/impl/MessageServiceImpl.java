package com.springoffice.message.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springoffice.global.util.DataResult;
import com.springoffice.message.entity.Message;
import com.springoffice.message.entity.json.SendMessageJson;
import com.springoffice.message.mapper.MessageMapper;
import com.springoffice.message.service.MessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("message-service")
public class MessageServiceImpl implements MessageService {
    @Resource
    private MessageMapper messageMapper;

    @Override
    public DataResult<List<Message>> getMessageList(Integer userId) {
        LambdaQueryWrapper<Message> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(userId != null, Message::getTarget, userId);
        List<Message> list = messageMapper.selectList(wrapper);
        return DataResult.ok("Message list查询成功", list);
    }

    @Override
    public DataResult<Message> getMessageById(Integer id) {
        Message message = messageMapper.selectById(id);
        if (message == null) {
            return DataResult.error("Message查询失败，ID:" + id + "不存在");
        }
        return DataResult.ok("Message查询成功", message);
    }

    @Override
    @Transactional
    public DataResult<List<Message>> sendMessages(SendMessageJson json) {
        boolean flag = true;
        List<Message> messages = json.generateMessages();
        for (Message message : messages) {
            flag = flag && messageMapper.insert(message) > 0;
        }
        if (!flag) {
            return DataResult.error("消息发送失败", messages);
        }
        return DataResult.ok("消息发送成功", messages);
    }

    @Override
    public DataResult<Object> deleteMessage(Integer id) {
        Message message = messageMapper.selectById(id);
        if (message == null) {
            return DataResult.error("Message删除失败，ID:" + id + "不存在");
        }
        int resultValue = messageMapper.deleteById(id);
        if (resultValue <= 0) {
            return DataResult.error("Message删除失败，ID:" + id);
        }
        return DataResult.ok("Message删除成功");
    }
}
