package com.springoffice.check.controller;

import com.springoffice.check.entity.CheckIn;
import com.springoffice.check.service.CheckInService;
import com.springoffice.global.util.DataResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/check")
public class CheckInController {
    @Resource
    private CheckInService checkInService;

    @PutMapping("/check_in")
    public DataResult<CheckIn> userCheckIn(@RequestBody CheckIn checkIn) {
        return checkInService.userCheckIn(checkIn);
    }
}
