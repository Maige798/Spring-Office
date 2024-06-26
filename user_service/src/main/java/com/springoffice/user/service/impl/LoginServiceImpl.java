package com.springoffice.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springoffice.global.util.DataResult;
import com.springoffice.user.entity.Login;
import com.springoffice.user.entity.User;
import com.springoffice.user.mapper.LoginMapper;
import com.springoffice.user.mapper.UserMapper;
import com.springoffice.user.service.LoginService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("login-service")
public class LoginServiceImpl implements LoginService {
    @Resource
    private LoginMapper loginMapper;
    @Resource
    private UserMapper userMapper;

    @Override
    public DataResult<Integer> savePassword(Login login) {
        int resultValue = loginMapper.insert(login);
        if (resultValue <= 0) {
            return DataResult.error("密码保存失败", -1);
        }
        return DataResult.ok("密码保存成功", 0);
    }

    @Override
    public DataResult<User> login(Login login) {
        LambdaQueryWrapper<Login> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Login::getId, login.getId())
                .eq(Login::getPassword, login.getPassword());
        List<Login> list = loginMapper.selectList(wrapper);
        if (list.isEmpty()) {
            return DataResult.error("登录失败，用户名或密码输入错误，请重试", null);
        }
        User user = userMapper.selectById(login.getId());
        if (user == null) { // maybe unreachable
            return DataResult.error("出现了神秘的数据库数据不一致错误", null);
        }
        return DataResult.ok("登录成功", user);
    }
}
