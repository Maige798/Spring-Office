package com.springoffice.book.controller;

import com.springoffice.book.entity.Contact;
import com.springoffice.book.entity.User;
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

    @DeleteMapping("/delete")
    public DataResult<Object> deleteContact(
            @RequestParam(name = "user_id") Integer userId,
            @RequestParam(name = "contact_id") Integer contactId
    ) {
        return contactService.deleteContact(userId, contactId);
    }
}
