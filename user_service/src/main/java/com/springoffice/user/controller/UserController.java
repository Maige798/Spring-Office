package com.springoffice.user.controller;

import com.springoffice.global.util.DataResult;
import com.springoffice.user.entity.User;
import com.springoffice.user.entity.json.UserBasicJson;
import com.springoffice.user.entity.json.UserDeptJson;
import com.springoffice.user.entity.json.UserRoleJson;
import com.springoffice.user.entity.json.UserStatusJson;
import com.springoffice.user.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    @PostMapping("/register/normal")
    public DataResult<User> normalRegister(@RequestBody User user) {
        return userService.register(user, false);
    }

    @PostMapping("/register/admin")
    public DataResult<User> adminRegister(@RequestBody User user) {
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

    @PutMapping("/update/status")
    public DataResult<User> updateUserStatus(@RequestBody UserStatusJson json) {
        User user = userService.getUserById(json.getId()).unwrap();
        if (user == null) {
            return DataResult.error("User更新失败，ID:" + json.getId() + "不存在");
        }
        json.update(user);
        return userService.updateUser(user);
    }

    @PutMapping("/update/role")
    public DataResult<User> updateUserRole(@RequestBody UserRoleJson json) {
        User user = userService.getUserById(json.getId()).unwrap();
        if (user == null) {
            return DataResult.error("User更新失败，ID:" + json.getId() + "不存在");
        }
        json.update(user);
        return userService.updateUser(user);
    }

    @PutMapping("/update/department")
    public DataResult<User> updateUserDepartment(@RequestBody UserDeptJson json) {
        User user = userService.getUserById(json.getId()).unwrap();
        if (user == null) {
            return DataResult.error("User更新失败，ID:" + json.getId() + "不存在");
        }
        json.update(user);
        return userService.updateUser(user);
    }

    @GetMapping("/query")
    public DataResult<User> getUserById(@RequestParam(name = "id") Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/query/message")
    public DataResult<List<User>> getUserList(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "sex", required = false) Integer sex,
            @RequestParam(name = "phone", required = false) String phone,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "status", required = false) Integer status,
            @RequestParam(name = "role_id", required = false) Integer roleId,
            @RequestParam(name = "dept_id", required = false) Integer deptId
    ) {
        User queryUser = new User(name, sex, phone, email, status, roleId, deptId);
        return userService.getUserList(queryUser);
    }
}
