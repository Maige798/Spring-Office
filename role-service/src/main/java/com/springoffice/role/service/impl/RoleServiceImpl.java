package com.springoffice.role.service.impl;

import com.springoffice.global.util.DataResult;
import com.springoffice.role.entity.Role;
import com.springoffice.role.mapper.PermissionMapper;
import com.springoffice.role.mapper.RoleMapper;
import com.springoffice.role.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("role-service")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public DataResult<Role> createRole(Role role) {
        return null;
    }
}
