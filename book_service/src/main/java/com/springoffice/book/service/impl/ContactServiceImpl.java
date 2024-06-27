package com.springoffice.book.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springoffice.book.client.UserClient;
import com.springoffice.book.entity.Contact;
import com.springoffice.book.entity.User;
import com.springoffice.book.mapper.ContactMapper;
import com.springoffice.book.service.ContactService;
import com.springoffice.global.util.DataResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("contact-service")
public class ContactServiceImpl implements ContactService {
    @Resource
    private ContactMapper contactMapper;
    @Resource
    private UserClient userClient;

    @Override
    public DataResult<Contact> addContact(Contact contact) {
        if (contact.getContactId().equals(0) || contact.getContactId().equals(contact.getUserId())) {
            return DataResult.error("Contact保存失败，目标不合法", contact);
        }
        int resultValue = contactMapper.insert(contact);
        if (resultValue <= 0) {
            return DataResult.error("Contact保存失败", contact);
        }
        return DataResult.ok("Contact保存成功", contact);
    }

    @Override
    public DataResult<List<User>> getContactList(Integer userId) {
        LambdaQueryWrapper<Contact> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(userId != null, Contact::getUserId, userId);
        List<Contact> contactList = contactMapper.selectList(wrapper);
        List<User> userList = new ArrayList<>();
        for (Contact contact : contactList) {
            User user = userClient.getUserById(contact.getContactId()).unwrap();
            if (user == null) { // maybe unreachable
                System.err.println("查询联系人时出现错误，User ID:" + contact.getContactId() + "不存在");
                continue;
            }
            userList.add(user);
        }
        return DataResult.ok("联系人列表查询成功", userList);
    }

    @Override
    public DataResult<Object> deleteContact(Integer userId, Integer contactId) {
        LambdaQueryWrapper<Contact> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Contact::getUserId, userId)
                .eq(Contact::getContactId, contactId);
        int resultValue = contactMapper.delete(queryWrapper);
        if (resultValue <= 0) {
            return DataResult.error("Contact删除失败");
        }
        return DataResult.ok("Contact删除成功");
    }
}
