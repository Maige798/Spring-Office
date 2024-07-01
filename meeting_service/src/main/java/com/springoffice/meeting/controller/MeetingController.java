package com.springoffice.meeting.controller;

import com.springoffice.global.util.DataResult;
import com.springoffice.meeting.entity.Meeting;
import com.springoffice.meeting.entity.json.CheckMeetingJson;
import com.springoffice.meeting.service.MeetingService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/meeting")
public class MeetingController {
    @Resource
    private MeetingService meetingService;

    @PostMapping("/create")
    DataResult<Meeting> createMeeting(@RequestBody Meeting meeting) {
        return meetingService.createMeeting(meeting);
    }

    @PutMapping("/update")
    DataResult<Meeting> updateMeeting(@RequestBody Meeting meeting) {
        return meetingService.updateMeeting(meeting);
    }

    @DeleteMapping("/delete/{id}")
    DataResult<Object> deleteMeeting(@PathVariable("id") Integer id) {
        return meetingService.deleteMeeting(id);
    }

    @GetMapping("/query")
    DataResult<Meeting> getMeetingById(@RequestParam(name = "id") Integer id) {
        return meetingService.getMeetingById(id);
    }

    @GetMapping("/query/user")
    DataResult<List<Meeting>> getMeetingByUserId(@RequestParam(name = "id") Integer userId) {
        return meetingService.getMeetingListByUserId(userId);
    }

    @GetMapping("/query/user_create")
    DataResult<List<Meeting>> getCreateMeetingByUserId(@RequestParam(name = "id") Integer userId) {
        return meetingService.getCreateMeetingListByUserId(userId);
    }

    @GetMapping("/query/department")
    DataResult<List<Meeting>> getMeetingByDeptId(@RequestParam(name = "id") Integer deptId) {
        return meetingService.getMeetingListByDeptId(deptId);
    }

    @PutMapping("/check")
    DataResult<Meeting> checkMeeting(@RequestBody CheckMeetingJson json) {
        return meetingService.checkMeeting(json);
    }
}
