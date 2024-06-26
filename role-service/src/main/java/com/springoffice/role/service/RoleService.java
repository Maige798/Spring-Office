package com.springoffice.role.service;

import com.springoffice.global.util.DataResult;
import com.springoffice.role.entity.Role;

import java.util.List;

public interface RoleService {
    DataResult<Role> createRole(Role role);

    DataResult<Role> getRoleById(Integer id);

    DataResult<List<Role>> getDepartmentRoles(Integer deptId);

    DataResult<Role> updateRole(Role role);

    DataResult<Object> deleteRole(Integer id);
}
