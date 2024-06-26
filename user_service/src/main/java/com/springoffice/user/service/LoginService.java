package com.springoffice.user.service;

import com.springoffice.global.util.DataResult;
import com.springoffice.user.entity.Login;
import com.springoffice.user.entity.User;

public interface LoginService {
    DataResult<Integer> savePassword(Login login);

    DataResult<User> login();
}
