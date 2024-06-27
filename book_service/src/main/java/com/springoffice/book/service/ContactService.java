package com.springoffice.book.service;

import com.springoffice.book.entity.Contact;
import com.springoffice.book.entity.User;
import com.springoffice.global.util.DataResult;

import java.util.List;

public interface ContactService {
    DataResult<Contact> addContact(Contact contact);

    DataResult<List<User>> getContactList(Integer userId);

    DataResult<Object> deleteContact(Integer userId, Integer contactId);
}
