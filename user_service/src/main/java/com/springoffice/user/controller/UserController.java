package com.springoffice.user.controller;

import com.springoffice.global.util.DataResult;
import com.springoffice.user.entity.User;
import com.springoffice.user.entity.json.UserBasicJson;
import com.springoffice.user.service.UserService;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/update/basic")
    public DataResult<User> updateUserBasic(@RequestBody UserBasicJson json) {
        User user = userService.getUserById(json.getId()).unwrap();
        if (user == null) {
            return DataResult.error("User更新失败，ID:" + json.getId() + "不存在");
        }
        json.update(user);
        return userService.updateUser(user);
    }
}
