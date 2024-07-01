package com.springoffice.message.client;

import com.springoffice.global.util.DataResult;
import com.springoffice.message.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("user-service")
public interface UserClient {
    @GetMapping("/user/query")
    DataResult<User> getUserById(@RequestParam(name = "id") Integer userId);
}
