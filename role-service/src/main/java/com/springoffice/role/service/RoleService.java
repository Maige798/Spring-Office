package com.springoffice.role.service;

import com.springoffice.global.util.DataResult;
import com.springoffice.role.entity.Role;

public interface RoleService {
    DataResult<Role> createRole(Role role);
}
