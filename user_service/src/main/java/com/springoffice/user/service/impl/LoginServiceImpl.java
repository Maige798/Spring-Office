package com.springoffice.user.service.impl;

import com.springoffice.global.util.DataResult;
import com.springoffice.user.entity.Login;
import com.springoffice.user.entity.User;
import com.springoffice.user.mapper.LoginMapper;
import com.springoffice.user.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("login-service")
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginMapper loginMapper;

    @Override
    public DataResult<Integer> savePassword(Login login) {
        int resultValue = loginMapper.insert(login);
        if (resultValue <= 0) {
            return DataResult.error("密码保存失败", -1);
        }
        return DataResult.ok("密码保存成功", 0);
    }

    @Override
    public DataResult<User> login() {
        return null;
    }
}
