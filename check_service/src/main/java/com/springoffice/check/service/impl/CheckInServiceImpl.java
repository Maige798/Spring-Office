package com.springoffice.check.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.springoffice.check.entity.CheckIn;
import com.springoffice.check.entity.Checker;
import com.springoffice.check.mapper.CheckInMapper;
import com.springoffice.check.mapper.CheckMapper;
import com.springoffice.check.service.CheckInService;
import com.springoffice.global.util.DataResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;

@Service("check-in-service")
public class CheckInServiceImpl implements CheckInService {
    @Resource
    private CheckInMapper checkInMapper;
    @Resource
    private CheckMapper checkMapper;

    @Override
    public DataResult<CheckIn> userCheckIn(CheckIn checkIn) {
        checkIn.setTime(new Timestamp(System.currentTimeMillis()));
        setUpStatus(checkIn);
        LambdaUpdateWrapper<CheckIn> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(CheckIn::getCheckId, checkIn.getCheckId())
                .eq(CheckIn::getUserId, checkIn.getUserId());
        int resultValue = checkInMapper.update(checkIn, wrapper);
        if (resultValue <= 0) {
            return DataResult.error("签到失败", checkIn);
        }
        return DataResult.ok("签到成功", checkIn);
    }

    private void setUpStatus(CheckIn checkIn) {
        Checker checker = checkMapper.selectById(checkIn.getCheckId());
        if (checker == null) {
            System.err.println("Check ID:" + checkIn.getCheckId() + "不存在");
            return;
        }
        if (checkIn.getTime().compareTo(checker.getStart()) < 0) {
            checkIn.setStatus(CheckIn.FAILED);
            return;
        }
        if (checkIn.getTime().compareTo(checker.getEnd()) < 0) {
            checkIn.setStatus(CheckIn.CHECKED);
            return;
        }
        checkIn.setStatus(CheckIn.LATE);
    }
}
