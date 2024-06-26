package com.springoffice.department.client;

import com.springoffice.department.entity.User;
import com.springoffice.department.entity.json.UserDeptJson;
import com.springoffice.global.util.DataResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/query/message")
    DataResult<List<User>> getUser(@RequestParam(name = "dept_id") Integer deptId);

    @PutMapping("/user/update/department")
    DataResult<User> updateUserDepartment(@RequestBody UserDeptJson json);

    @GetMapping("/user/query")
    DataResult<User> getUserById(@RequestParam(name = "id") Integer userId);
}
