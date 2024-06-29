package com.springoffice.user.client;

import com.springoffice.global.util.DataResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("role-service")
public interface RoleClient {
    @GetMapping("/role/query/name")
    DataResult<String> getRoleNameById(@RequestParam(name = "id") Integer roleId);
}
