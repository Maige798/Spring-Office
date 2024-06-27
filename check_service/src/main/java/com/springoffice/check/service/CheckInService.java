package com.springoffice.check.service;

import com.springoffice.check.entity.CheckIn;
import com.springoffice.global.util.DataResult;

public interface CheckInService {
    DataResult<CheckIn> userCheckIn(CheckIn checkIn);
}
