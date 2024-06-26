package com.springoffice.role.controller;

import com.springoffice.global.util.DataResult;
import com.springoffice.role.entity.Role;
import com.springoffice.role.service.RoleService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    @PostMapping("/create")
    public DataResult<Role> createRole(@RequestBody Role role) {
        return roleService.createRole(role);
    }

    @GetMapping("/query")
    public DataResult<Role> getRoleById(@RequestParam(name = "id") Integer id) {
        return roleService.getRoleById(id);
    }

    @GetMapping("/query/department")
    public DataResult<List<Role>> getDepartmentRoles(@RequestParam(name = "dept_id", required = false) Integer deptId) {
        return roleService.getDepartmentRoles(deptId);
    }

    @PutMapping("/update")
    public DataResult<Role> updateRole(@RequestBody Role role) {
        return roleService.updateRole(role);
    }

    @DeleteMapping("/delete")
    public DataResult<Object> updateRole(@RequestParam(name = "id") Integer id) {
        return roleService.deleteRole(id);
    }
}
