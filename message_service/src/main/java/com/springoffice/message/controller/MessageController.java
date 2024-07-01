package com.springoffice.message.controller;

import com.springoffice.global.util.DataResult;
import com.springoffice.message.entity.Message;
import com.springoffice.message.entity.json.SendMessageJson;
import com.springoffice.message.service.MessageService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    @Resource
    private MessageService messageService;

    @GetMapping("/query/list")
    public DataResult<List<Message>> getMessageList(@RequestParam(name = "user_id") Integer userId) {
        return messageService.getMessageList(userId);
    }

    @GetMapping("/query")
    public DataResult<Message> getMessageById(@RequestParam(name = "id") Integer id) {
        return messageService.getMessageById(id);
    }

    @PostMapping("/send")
    public DataResult<List<Message>> sendMessages(@RequestBody SendMessageJson json) {
        return messageService.sendMessages(json);
    }

    @DeleteMapping("/delete/{id}")
    public DataResult<Object> deleteMessage(@PathVariable("id") Integer id) {
        return messageService.deleteMessage(id);
    }
}
