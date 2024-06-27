package com.springoffice.book.controller;

import com.springoffice.book.entity.Conversation;
import com.springoffice.book.entity.ConversationMessage;
import com.springoffice.book.service.ConversationMessageService;
import com.springoffice.book.service.ConversationService;
import com.springoffice.global.util.DataResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/book/conversation")
public class ConversationController {
    @Resource
    private ConversationService conversationService;
    @Resource
    private ConversationMessageService conversationMessageService;

    @PostMapping("/create")
    public DataResult<Conversation> createConversation(@RequestBody Conversation conversation) {
        return conversationService.createConversation(conversation);
    }

    @GetMapping("/list/query")
    public DataResult<List<Conversation>> getConversationList(@RequestParam(name = "id") Integer userId) {
        return conversationService.getConversationList(userId);
    }

    @GetMapping("/query/message")
    public DataResult<Conversation> getConversation(
            @RequestParam(name = "a_id") Integer aId,
            @RequestParam(name = "b_id") Integer bId
    ) {
        return conversationService.getConversation(aId, bId);
    }

    @GetMapping("/query")
    public DataResult<Conversation> getConversationById(@RequestParam(name = "id") Integer id) {
        return conversationService.getConversationById(id);
    }

    @DeleteMapping("/delete")
    public DataResult<Object> deleteConversation(@RequestParam(name = "id") Integer id) {
        return conversationService.deleteConversation(id);
    }

    @PostMapping("/send")
    public DataResult<ConversationMessage> sendMessage(@RequestBody ConversationMessage message) {
        return conversationMessageService.sendMessage(message);
    }
}
