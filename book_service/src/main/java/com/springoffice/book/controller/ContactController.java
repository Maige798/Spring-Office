package com.springoffice.book.controller;

import com.springoffice.book.entity.Contact;
import com.springoffice.book.entity.User;
import com.springoffice.book.entity.json.DeleteContactJson;
import com.springoffice.book.service.ContactService;
import com.springoffice.global.util.DataResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/book/contact")
public class ContactController {
    @Resource
    private ContactService contactService;

    @PostMapping("/add")
    public DataResult<Contact> addContact(@RequestBody Contact contact) {
        return contactService.addContact(contact);
    }

    @GetMapping("/query")
    public DataResult<List<User>> getUserContacts(@RequestParam(name = "id") Integer userId) {
        return contactService.getContactList(userId);
    }

    @PostMapping("/delete")
    public DataResult<Object> deleteContact(@RequestBody DeleteContactJson json) {
        return contactService.deleteContact(json.getUserId(), json.getContactId());
    }
}
