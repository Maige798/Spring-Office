package com.springoffice.meeting.service;

import com.springoffice.global.util.DataResult;
import com.springoffice.meeting.entity.Meeting;
import com.springoffice.meeting.entity.json.CheckMeetingJson;

import java.util.List;

public interface MeetingService {
    DataResult<Meeting> createMeeting(Meeting meeting);

    DataResult<Meeting> updateMeeting(Meeting meeting);

    DataResult<Meeting> getMeetingById(Integer id);

    DataResult<List<Meeting>> getMeetingListByUserId(Integer userId);

    DataResult<List<Meeting>> getMeetingListByDeptId(Integer deptId);

    DataResult<Object> deleteMeeting(Integer id);

    DataResult<Meeting> checkMeeting(CheckMeetingJson json);
}
