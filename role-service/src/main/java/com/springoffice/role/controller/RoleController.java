package com.springoffice.role.controller;

import com.springoffice.global.util.DataResult;
import com.springoffice.role.entity.Role;
import com.springoffice.role.service.RoleService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping("/create")
    public DataResult<Role> createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }
}
