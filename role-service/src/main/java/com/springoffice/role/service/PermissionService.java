package com.springoffice.role.service;

import com.springoffice.global.util.DataResult;
import com.springoffice.role.entity.Permission;

import java.util.List;

public interface PermissionService {
    DataResult<List<Permission>> permissionList(Integer id);

    DataResult<List<Permission>> getPermissionListByUserId(Integer userId);
}
