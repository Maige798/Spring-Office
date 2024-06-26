package com.springoffice.user.service;

import com.springoffice.global.util.DataResult;
import com.springoffice.user.entity.User;

public interface LoginService {
    DataResult<User> login();
}
