package com.springoffice.role.service.impl;

import com.springoffice.global.util.DataResult;
import com.springoffice.role.entity.Permission;
import com.springoffice.role.entity.Role;
import com.springoffice.role.entity.RolePermission;
import com.springoffice.role.mapper.RoleMapper;
import com.springoffice.role.mapper.RolePermissionMapper;
import com.springoffice.role.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service("role-service")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;

    @Override
    @Transactional
    public DataResult<Role> createRole(Role role) {
        int resultValue = roleMapper.insert(role);
        if (resultValue <= 0) {
            return DataResult.error("role创建失败", role);
        }
        boolean flag = true;
        for (Permission permission : role.getPermissions()) {
            flag = flag && saveRolePermission(new RolePermission(role.getId(), permission.getId()));
        }
        if (!flag) {
            return DataResult.error("role权限绑定失败", role);
        }
        return DataResult.ok("role创建成功", role);
    }

    private boolean saveRolePermission(RolePermission rolePermission) {
        return rolePermissionMapper.insert(rolePermission) > 0;
    }
}
