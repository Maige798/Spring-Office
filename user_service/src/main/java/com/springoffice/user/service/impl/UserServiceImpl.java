package com.springoffice.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springoffice.global.util.DataResult;
import com.springoffice.user.client.DepartmentClient;
import com.springoffice.user.client.RoleClient;
import com.springoffice.user.entity.Login;
import com.springoffice.user.entity.User;
import com.springoffice.user.entity.client.Department;
import com.springoffice.user.mapper.UserMapper;
import com.springoffice.user.service.LoginService;
import com.springoffice.user.service.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("user-service")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private LoginService loginService;
    @Resource
    private DepartmentClient departmentClient;
    @Resource
    private RoleClient roleClient;

    @Override
    @Transactional
    public DataResult<User> register(User user, boolean isAdmin) {
        user.setIsAdmin(isAdmin ? 1 : 0);
        System.out.println(user);
        int resultValue = userMapper.insert(user);
        if (resultValue <= 0) {
            return DataResult.error("User创建失败", null);
        }
        if (!loginService.savePassword(new Login(user)).success()) {
            return DataResult.error("User密码保存失败", null);
        }
        if (isAdmin) {
            Department department = departmentClient.createDepartment(new Department(user)).unwrap();
            if (department == null) {
                return DataResult.error("User创建部门失败", user);
            }
            user.setDeptId(department.getId());
            userMapper.updateById(user);
        }
        return DataResult.ok("User创建成功", user);
    }

    @Override
    public DataResult<User> getUserById(Integer id) {
        User user = userMapper.selectById(id);
        if (user == null) {
            return DataResult.error("User查询失败，id:" + id + "不存在", null);
        }
        updateUserMessages(user);
        return DataResult.ok("User查询成功", user);
    }

    @Override
    public DataResult<User> updateUser(User user) {
        int resultValue = userMapper.updateById(user);
        if (resultValue <= 0) {
            return DataResult.error("User更新失败", user);
        }
        return DataResult.ok("User更新成功", user);
    }

    @Override
    public DataResult<List<User>> getUserList(User user) {
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(user.getName() != null, User::getName, user.getName())
                .eq(user.getSex() != null, User::getSex, user.getSex())
                .eq(user.getPhone() != null, User::getPhone, user.getPhone())
                .eq(user.getEmail() != null, User::getEmail, user.getEmail())
                .eq(user.getStatus() != null, User::getStatus, user.getStatus())
                .eq(user.getRoleId() != null, User::getRoleId, user.getRoleId())
                .eq(user.getDeptId() != null, User::getDeptId, user.getDeptId());
        List<User> userList = userMapper.selectList(wrapper);
        userList.forEach(this::updateUserMessages);
        return DataResult.ok("User list查询成功", userList);
    }

    private void updateUserMessages(User user) {
        user.setDeptName(departmentClient.getDepartmentNameById(user.getDeptId()).unwrap());
        user.setRoleName(roleClient.getRoleNameById(user.getRoleId()).unwrap());
    }
}
