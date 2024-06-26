package com.springoffice.user.controller;

import com.springoffice.global.util.DataResult;
import com.springoffice.user.entity.User;
import com.springoffice.user.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register/normal")
    public DataResult<User> normalRegister(@RequestBody User user) {
        System.out.println(user);
        return userService.register(user, false);
    }

    @PostMapping("/register/admin")
    public DataResult<User> adminRegister(@RequestBody User user) {
        System.out.println(user);
        return userService.register(user, true);
    }
}
