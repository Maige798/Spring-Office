package com.springoffice.user.service.impl;

import com.springoffice.global.util.DataResult;
import com.springoffice.user.entity.Login;
import com.springoffice.user.entity.User;
import com.springoffice.user.mapper.UserMapper;
import com.springoffice.user.service.LoginService;
import com.springoffice.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("user-service")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginService loginService;

    @Override
    @Transactional
    public DataResult<User> register(User user, boolean isAdmin) {
        user.setIsAdmin(isAdmin ? 1 : 0);
        System.out.println(user);
        int resultValue = userMapper.insert(user);
        if (resultValue <= 0) {
            return DataResult.error("User创建失败", null);
        }
        if (!loginService.savePassword(new Login(user)).success()) {
            return DataResult.error("User创建失败，密码保存失败", null);
        }
        return DataResult.ok("User创建成功", user);
    }
}
