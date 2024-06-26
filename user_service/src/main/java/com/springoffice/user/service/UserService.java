package com.springoffice.user.service;

import com.springoffice.global.util.DataResult;
import com.springoffice.user.entity.User;

public interface UserService {
    DataResult<User> register(User user, boolean isAdmin);

    DataResult<User> getUserById(Integer id);

    DataResult<User> updateUser(User user);
}
