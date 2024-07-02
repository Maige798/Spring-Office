package com.springoffice.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springoffice.global.util.DataResult;
import com.springoffice.role.client.UserClient;
import com.springoffice.role.entity.Permission;
import com.springoffice.role.entity.Role;
import com.springoffice.role.entity.User;
import com.springoffice.role.mapper.PermissionMapper;
import com.springoffice.role.service.PermissionService;
import com.springoffice.role.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service("permission-service")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;
    @Resource
    private UserClient userClient;
    @Resource
    private RoleService roleService;

    @Override
    public DataResult<List<Permission>> permissionList(Integer id) {
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(id != null, Permission::getId, id);
        List<Permission> permissionList = permissionMapper.selectList(wrapper);
        return DataResult.ok("Permission list查询成功", permissionList);
    }

    @Override
    public DataResult<List<Permission>> getPermissionListByUserId(Integer userId) {
        DataResult<User> userResult = userClient.getUserById(userId);
        if (!userResult.success()) {
            return DataResult.error("User ID:" + userId + "不存在");
        }
        User user = userResult.unwrap();
        if (user.getIsAdmin().equals(1)) {
            return permissionList(null);
        }
        if (user.getDeptId().equals(0)) {
            return DataResult.ok("权限查询成功", new ArrayList<>());
        }
        DataResult<Role> roleResult = roleService.getRoleById(user.getRoleId());
        if (!roleResult.success()) {
            return DataResult.error(roleResult.getMessage());
        }
        return DataResult.ok("权限查询成功", roleResult.unwrap().getPermissions());
    }
}
