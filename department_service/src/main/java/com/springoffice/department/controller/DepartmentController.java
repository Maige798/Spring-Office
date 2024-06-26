package com.springoffice.department.controller;

import com.springoffice.department.entity.Department;
import com.springoffice.department.entity.User;
import com.springoffice.department.service.DepartmentService;
import com.springoffice.global.util.DataResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/members/query")
    public DataResult<List<User>> getDepartmentMembers(@RequestParam(name = "id") Integer dept_id) {
        return departmentService.getDepartmentMembers(dept_id);
    }
}
