package com.springoffice.department.controller;

import com.springoffice.department.entity.Department;
import com.springoffice.department.service.DepartmentService;
import com.springoffice.global.util.DataResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/department")
public class DepartmentController {
    @Resource
    private DepartmentService departmentService;

    @PostMapping("/create")
    public DataResult<Department> createDepartment(@RequestBody Department department) {
        return departmentService.createDepartment(department);
    }

    @PutMapping("/update")
    public DataResult<Department> updateDepartment(@RequestBody Department department) {
        return departmentService.updateDepartment(department);
    }
}
