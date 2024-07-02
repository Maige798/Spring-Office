package com.springoffice.role.controller;

import com.springoffice.global.util.DataResult;
import com.springoffice.role.entity.Permission;
import com.springoffice.role.service.PermissionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class PermissionController {
    @Resource
    private PermissionService permissionService;

    @GetMapping("/permission/list")
    public DataResult<List<Permission>> getPermissionList(@RequestParam(name = "id", required = false) Integer id) {
        return permissionService.permissionList(id);
    }

    @GetMapping("/permission/user")
    public DataResult<List<Permission>> getUserPermissionList(@RequestParam(name = "user_id") Integer userId) {
        return permissionService.getPermissionListByUserId(userId);
    }
}
