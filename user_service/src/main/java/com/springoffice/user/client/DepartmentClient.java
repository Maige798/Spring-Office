package com.springoffice.user.client;

import com.springoffice.global.util.DataResult;
import com.springoffice.user.entity.client.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient("department-service")
public interface DepartmentClient {
    @PostMapping("/department/create")
    DataResult<Department> createDepartment(@RequestBody Department department);
}
