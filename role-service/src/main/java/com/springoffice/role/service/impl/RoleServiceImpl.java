package com.springoffice.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springoffice.global.util.DataResult;
import com.springoffice.role.entity.Permission;
import com.springoffice.role.entity.Role;
import com.springoffice.role.entity.RolePermission;
import com.springoffice.role.mapper.PermissionMapper;
import com.springoffice.role.mapper.RoleMapper;
import com.springoffice.role.mapper.RolePermissionMapper;
import com.springoffice.role.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("role-service")
public class RoleServiceImpl implements RoleService {
    @Resource
    private RoleMapper roleMapper;
    @Resource
    private RolePermissionMapper rolePermissionMapper;
    @Resource
    private PermissionMapper permissionMapper;

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

    @Override
    public DataResult<Role> getRoleById(Integer id) {
        Role role = roleMapper.selectById(id);
        if (role == null) {
            return DataResult.error("Role查询失败，ID:" + id + "不存在");
        }
        loadPermissions(role);
        return DataResult.ok("Role查询成功", role);
    }

    @Override
    public DataResult<List<Role>> getDepartmentRoles(Integer deptId) {
        LambdaQueryWrapper<Role> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(deptId != null, Role::getDeptId, deptId);
        List<Role> list = roleMapper.selectList(wrapper);
        list.forEach(this::loadPermissions);
        return DataResult.ok("Role list查询成功", list);
    }

    @Override
    public DataResult<Role> updateRole(Role role) {
        int resultValue = roleMapper.updateById(role);
        if (resultValue <= 0) {
            return DataResult.error("Role更新失败", role);
        }
        removeRolePermission(role);
        boolean flag = true;
        for (Permission permission : role.getPermissions()) {
            flag = flag && saveRolePermission(new RolePermission(role.getId(), permission.getId()));
        }
        if (!flag) {
            return DataResult.error("role权限绑定失败", role);
        }
        return DataResult.ok("Role更新成功", role);
    }

    @Override
    public DataResult<Object> deleteRole(Integer id) {
        Role role = roleMapper.selectById(id);
        if (role == null) {
            return DataResult.error("Role删除失败，ID:" + id + "不存在");
        }
        int resultValue = roleMapper.deleteById(id);
        if (resultValue <= 0) {
            return DataResult.error("Role删除失败");
        }
        return DataResult.ok("Role删除成功");
    }

    @Override
    public DataResult<String> getRoleNameById(Integer id) {
        Role role = roleMapper.selectById(id);
        if (role == null) {
            return DataResult.error("Role name查询失败，ID:" + id + "不存在");
        }
        return DataResult.ok("Role name查询成功", role.getName());
    }

    private void loadPermissions(Role role) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, role.getId());
        List<RolePermission> rolePermissionList = rolePermissionMapper.selectList(wrapper);
        List<Permission> permissions = new ArrayList<>();
        for (RolePermission rolePermission : rolePermissionList) {
            Permission permission = permissionMapper.selectById(rolePermission.getPermissionId());
            if (permission != null) {
                permissions.add(permission);
            } else {
                System.err.println("出现了数据库不一致性错误，Permission ID:" + rolePermission.getPermissionId() + "不存在");
            }
        }
        role.setPermissions(permissions);
    }

    private boolean saveRolePermission(RolePermission rolePermission) {
        return rolePermissionMapper.insert(rolePermission) > 0;
    }

    private void removeRolePermission(Role role) {
        LambdaQueryWrapper<RolePermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RolePermission::getRoleId, role.getId());
        int resultValue = rolePermissionMapper.delete(wrapper);
        if (resultValue < 0) {
            System.out.println("删除角色权限出错，Role ID:" + role.getId());
        }
    }
}
