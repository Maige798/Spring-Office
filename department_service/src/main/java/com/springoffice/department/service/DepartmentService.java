package com.springoffice.department.service;

import com.springoffice.department.entity.Department;
import com.springoffice.department.entity.User;
import com.springoffice.department.entity.json.ChangeMemberJson;
import com.springoffice.global.util.DataResult;

import java.util.List;

public interface DepartmentService {
    DataResult<Department> createDepartment(Department department);

    DataResult<Department> updateDepartment(Department department);

    DataResult<Department> getDepartmentById(Integer id);

    DataResult<List<User>> getDepartmentMembers(Integer id);

    DataResult<User> addMember(ChangeMemberJson json);

    DataResult<User> removeMember(ChangeMemberJson json);

    DataResult<String> getDepartmentNameById(Integer id);
}
