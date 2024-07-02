package com.springoffice.fish.client;

import com.springoffice.fish.entity.User;
import com.springoffice.global.util.DataResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("department-service")
public interface DepartmentClient {
    @GetMapping("/department/members/query")
    DataResult<List<User>> getDepartmentMembers(@RequestParam(name = "id") Integer dept_id);
}
