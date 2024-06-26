package com.springoffice.department.service;

import com.springoffice.department.entity.Department;
import com.springoffice.global.util.DataResult;

public interface DepartmentService {
    DataResult<Department> createDepartment(Department department);
}
