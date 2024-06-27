package com.springoffice.check.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.springoffice.check.entity.CheckIn;
import com.springoffice.check.mapper.CheckInMapper;
import com.springoffice.check.service.CheckInService;
import com.springoffice.global.util.DataResult;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("check-in-service")
public class CheckInServiceImpl implements CheckInService {
    @Resource
    private CheckInMapper checkInMapper;

    @Override
    public DataResult<CheckIn> userCheckIn(CheckIn checkIn) {
        LambdaUpdateWrapper<CheckIn> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(CheckIn::getCheckId, checkIn.getCheckId())
                .eq(CheckIn::getUserId, checkIn.getUserId());
        int resultValue = checkInMapper.update(checkIn, wrapper);
        if (resultValue <= 0) {
            return DataResult.error("签到失败", checkIn);
        }
        return DataResult.ok("签到成功", checkIn);
    }
}
