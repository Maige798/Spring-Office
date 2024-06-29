package com.springoffice.user.client;

import com.springoffice.global.util.DataResult;
import com.springoffice.user.entity.client.Department;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient("department-service")
public interface DepartmentClient {
    @PostMapping("/department/create")
    DataResult<Department> createDepartment(@RequestBody Department department);

    @GetMapping("/department/query/name")
    DataResult<String> getDepartmentNameById(@RequestParam(name = "id") Integer deptId);
}
