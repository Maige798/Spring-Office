package com.springoffice.user.controller;

import com.springoffice.global.util.DataResult;
import com.springoffice.user.entity.Login;
import com.springoffice.user.entity.User;
import com.springoffice.user.service.LoginService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class LoginController {
    @Resource
    private LoginService loginService;

    @PostMapping("/login")
    public DataResult<User> login(@RequestBody Login login) {
        return loginService.login(login);
    }
}
