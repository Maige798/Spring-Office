package com.springoffice.role.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springoffice.global.util.DataResult;
import com.springoffice.role.entity.Permission;
import com.springoffice.role.mapper.PermissionMapper;
import com.springoffice.role.service.PermissionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("permission-service")
public class PermissionServiceImpl implements PermissionService {
    @Resource
    private PermissionMapper permissionMapper;

    @Override
    public DataResult<List<Permission>> permissionList(Integer id) {
        LambdaQueryWrapper<Permission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(id != null, Permission::getId, id);
        List<Permission> permissionList = permissionMapper.selectList(wrapper);
        return DataResult.ok("Permission list查询成功", permissionList);
    }
}
