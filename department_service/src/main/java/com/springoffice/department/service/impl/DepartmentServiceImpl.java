package com.springoffice.department.service.impl;

import com.springoffice.department.entity.Department;
import com.springoffice.department.mapper.DepartmentMapper;
import com.springoffice.department.service.DepartmentService;
import com.springoffice.global.util.DataResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("department-service")
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;

    @Override
    public DataResult<Department> createDepartment(Department department) {
        int resultValue = departmentMapper.insert(department);
        if (resultValue <= 0) {
            return DataResult.error("创建部门失败", department);
        }
        return DataResult.ok("创建部门成功", department);
    }
}
