package com.springoffice.department.controller;

import com.springoffice.department.entity.Department;
import com.springoffice.department.service.DepartmentService;
import com.springoffice.global.util.DataResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @RequestMapping("/create")
    public DataResult<Department> createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }
}
