package com.springoffice.check.client;

import com.springoffice.check.entity.User;
import com.springoffice.global.util.DataResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/query")
    DataResult<User> getUserById(@RequestParam(name = "id") Integer userId);
}
