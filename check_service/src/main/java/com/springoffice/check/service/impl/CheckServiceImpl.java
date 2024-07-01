package com.springoffice.check.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springoffice.check.client.UserClient;
import com.springoffice.check.entity.CheckIn;
import com.springoffice.check.entity.Checker;
import com.springoffice.check.entity.User;
import com.springoffice.check.mapper.CheckInMapper;
import com.springoffice.check.mapper.CheckMapper;
import com.springoffice.check.service.CheckService;
import com.springoffice.global.util.DataResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service("check-service")
public class CheckServiceImpl implements CheckService {
    @Resource
    private CheckMapper checkMapper;
    @Resource
    private CheckInMapper checkInMapper;
    @Resource
    private UserClient userClient;

    @Override
    @Transactional
    public DataResult<Checker> createCheck(Checker checker) {
        if (checkTimeWrong(checker)) {
            return DataResult.error("时间错误，start不能晚于end，考勤创建失败", checker);
        }
        int resultValue = checkMapper.insert(checker);
        if (resultValue <= 0) {
            return DataResult.error("考勤创建失败", checker);
        }
        String saveMessage = saveCheckInList(checker);
        return DataResult.ok("考勤创建成功" + saveMessage, checker);
    }

    @Override
    public DataResult<Checker> getCheckById(Integer id) {
        Checker checker = checkMapper.selectById(id);
        if (checker == null) {
            return DataResult.error("Check查询失败，ID:" + id + "不存在");
        }
        loadChecker(checker);
        return DataResult.ok("Check查询成功", checker);
    }

    @Override
    public DataResult<List<Checker>> getCheckListByUser(Integer userId) {
        LambdaQueryWrapper<CheckIn> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CheckIn::getUserId, userId);
        List<CheckIn> checkInList = checkInMapper.selectList(wrapper);
        List<Checker> checkerList = new ArrayList<>();
        for (CheckIn checkIn : checkInList) {
            checkerList.add(getCheckById(checkIn.getCheckId()).unwrap());
        }
        return DataResult.ok("Check list查询成功", checkerList);
    }

    @Override
    public DataResult<List<Checker>> getUncheckedListByUser(Integer userId) {
        LambdaQueryWrapper<CheckIn> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CheckIn::getUserId, userId)
                .in(CheckIn::getStatus, Arrays.asList(0, 3));
        List<CheckIn> checkInList = checkInMapper.selectList(wrapper);
        List<Checker> checkerList = new ArrayList<>();
        for (CheckIn checkIn : checkInList) {
            checkerList.add(getCheckById(checkIn.getCheckId()).unwrap());
        }
        return DataResult.ok("Check list查询成功", checkerList);
    }

    @Override
    public DataResult<List<Checker>> getCheckListByDepartment(Integer deptId) {
        LambdaQueryWrapper<Checker> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Checker::getDeptId, deptId);
        List<Checker> checkerList = checkMapper.selectList(wrapper);
        checkerList.forEach(this::loadChecker);
        return DataResult.ok("Check list查询成功", checkerList);
    }

    public boolean checkTimeWrong(Checker checker) {
        return checker.getStart().compareTo(checker.getEnd()) >= 0;
    }

    private String saveCheckInList(Checker checker) {
        if (checker.getMemberIds() == null)
            return "";
        checker.setCheckInList(new ArrayList<>());
        StringBuilder builder = new StringBuilder();
        for (Integer memberId : checker.getMemberIds()) {
            DataResult<CheckIn> result = saveCheckIn(checker, memberId);
            if (result.success()) {
                checker.getCheckInList().add(result.unwrap());
            } else {
                builder.append(result.getMessage());
            }
        }
        return builder.toString();
    }

    private DataResult<CheckIn> saveCheckIn(Checker checker, Integer userId) {
        User user = userClient.getUserById(userId).unwrap();
        if (user == null) {
            return DataResult.error(" *签到记录写入失败，User ID:" + userId + "不存在");
        }
        CheckIn checkIn = new CheckIn(checker.getId(), userId, checker.getStart(), CheckIn.UNCHECK);
        checkIn.setUser(user);
        int resultValue = checkInMapper.insert(checkIn);
        if (resultValue <= 0) {
            return DataResult.error(" *签到记录写入失败");
        }
        return DataResult.ok("签到记录写入成功", checkIn);
    }

    private void loadCheckInList(Checker checker) {
        LambdaQueryWrapper<CheckIn> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CheckIn::getCheckId, checker.getId());
        List<CheckIn> list = checkInMapper.selectList(wrapper);
        list.forEach(this::loadCheckInUser);
        checker.setCheckInList(list);
    }

    private void loadCheckInUser(CheckIn checkIn) {
        User user = userClient.getUserById(checkIn.getUserId()).unwrap();
        if (user == null) {
            System.err.println("User ID:" + checkIn.getUserId() + "不存在");
        }
        checkIn.setUser(user);
    }

    private void countAttendanceRate(Checker checker) {
        checker.setAttendanceRate((double) 0);
        if (checker.getCheckInList() == null || checker.getCheckInList().isEmpty())
            return;
        double rate = checker.getCheckInList().size();
        double count = 0;
        for (CheckIn checkIn : checker.getCheckInList()) {
            if (checkIn.getStatus().equals(CheckIn.CHECKED)) {
                count += 1;
            }
        }
        rate = count / rate;
        checker.setAttendanceRate(rate);
    }

    private void loadChecker(Checker checker) {
        loadCheckInList(checker);
        countAttendanceRate(checker);
    }
}
