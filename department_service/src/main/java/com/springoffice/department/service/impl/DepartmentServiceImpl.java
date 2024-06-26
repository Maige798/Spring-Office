package com.springoffice.department.service.impl;

import com.springoffice.department.client.UserClient;
import com.springoffice.department.entity.Department;
import com.springoffice.department.entity.User;
import com.springoffice.department.entity.json.AddMemberJson;
import com.springoffice.department.entity.json.UserDeptJson;
import com.springoffice.department.mapper.DepartmentMapper;
import com.springoffice.department.service.DepartmentService;
import com.springoffice.global.util.DataResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("department-service")
public class DepartmentServiceImpl implements DepartmentService {
    @Resource
    private DepartmentMapper departmentMapper;
    @Resource
    private UserClient userClient;

    @Override
    public DataResult<Department> createDepartment(Department department) {
        int resultValue = departmentMapper.insert(department);
        if (resultValue <= 0) {
            return DataResult.error("创建部门失败", department);
        }
        return DataResult.ok("创建部门成功", department);
    }

    @Override
    public DataResult<Department> updateDepartment(Department department) {
        int resultValue = departmentMapper.updateById(department);
        if (resultValue <= 0) {
            return DataResult.error("部门更新失败", department);
        }
        return DataResult.ok("部门更新成功", department);
    }

    @Override
    public DataResult<Department> getDepartmentById(Integer id) {
        Department department = departmentMapper.selectById(id);
        if (department == null) {
            return DataResult.error("部门查询失败，ID:" + id + "不存在");
        }
        return DataResult.ok("部门查询成功", department);
    }

    @Override
    public DataResult<List<User>> getDepartmentMembers(Integer id) {
        return userClient.getUser(id);
    }

    @Override
    public DataResult<User> addMember(AddMemberJson json) {
        User user = userClient.getUserById(json.getMemberId()).unwrap();
        if (user == null) {
            return DataResult.error("账号:" + json.getMemberId() + "不存在，添加成员失败");
        }
        if (user.hasDepartment()) {
            return DataResult.error("账号:" + json.getMemberId() + "已经有部门了，添加成员失败");
        }
        userClient.updateUserDepartment(new UserDeptJson(json.getMemberId(), json.getDeptId()));
        User result = userClient.getUserById(json.getMemberId()).unwrap();
        return DataResult.ok("添加成员成功", result);
    }
}
