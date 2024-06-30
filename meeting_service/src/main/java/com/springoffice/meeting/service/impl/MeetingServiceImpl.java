package com.springoffice.meeting.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.springoffice.global.util.DataResult;
import com.springoffice.meeting.client.UserClient;
import com.springoffice.meeting.entity.Attendance;
import com.springoffice.meeting.entity.Meeting;
import com.springoffice.meeting.entity.User;
import com.springoffice.meeting.entity.json.CheckMeetingJson;
import com.springoffice.meeting.mapper.AttendanceMapper;
import com.springoffice.meeting.mapper.MeetingMapper;
import com.springoffice.meeting.service.MeetingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("meeting-service")
public class MeetingServiceImpl implements MeetingService {
    public static final int CHECK_PASS = 1;
    public static final int CHECK_REJECT = 2;
    @Resource
    private MeetingMapper meetingMapper;
    @Resource
    private AttendanceMapper attendanceMapper;
    @Resource
    private UserClient userClient;

    @Override
    @Transactional
    public DataResult<Meeting> createMeeting(Meeting meeting) {
        meeting.setStatus(0);
        meeting.setCreateTime(new Timestamp(System.currentTimeMillis()));
        int resultValue = meetingMapper.insert(meeting);
        if (resultValue <= 0) {
            return DataResult.error("Meeting创建失败", meeting);
        }
        String saveAttenderMessage = saveAttenderList(meeting);
        // todo 向审核人员发送待审消息
        return DataResult.ok("Meeting创建成功" + saveAttenderMessage, meeting);
    }

    @Override
    public DataResult<Meeting> updateMeeting(Meeting meeting) {
        meeting.setStatus(0);
        int resultValue = meetingMapper.updateById(meeting);
        if (resultValue <= 0) {
            return DataResult.error("Meeting更新失败", meeting);
        }
        removeAttenderList(meeting.getId());
        String saveAttenderMessage = saveAttenderList(meeting);
        // todo 向审核人员发送待审消息
        return DataResult.ok("Meeting更新成功" + saveAttenderMessage, meeting);
    }

    @Override
    public DataResult<Meeting> getMeetingById(Integer id) {
        Meeting meeting = meetingMapper.selectById(id);
        if (meeting == null) {
            return DataResult.error("Meeting查询失败，ID:" + id + "不存在");
        }
        getAttenderList(meeting);
        return DataResult.ok("Meeting查询成功", meeting);
    }

    @Override
    public DataResult<List<Meeting>> getMeetingListByUserId(Integer userId) {
        List<Meeting> meetingList = getCreateMeetingByUserId(userId);
        Set<Integer> idSet = new HashSet<>(); // 用于去重
        for (Meeting meeting : meetingList) {
            idSet.add(meeting.getId());
        }
        List<Meeting> tempList = getAttendMeetingByUserId(userId);
        for (Meeting meeting : tempList) {
            if (idSet.contains(meeting.getId())) continue;
            meetingList.add(meeting);
        }
        return DataResult.ok("Meeting list查询成功", meetingList);
    }

    @Override
    public DataResult<List<Meeting>> getMeetingListByDeptId(Integer deptId) {
        LambdaQueryWrapper<Meeting> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Meeting::getDeptId, deptId);
        List<Meeting> meetingList = meetingMapper.selectList(wrapper);
        meetingList.forEach(this::getAttenderList);
        return DataResult.ok("Meeting list查询成功", meetingList);
    }

    @Override
    public DataResult<Object> deleteMeeting(Integer id) {
        int resultValue = meetingMapper.deleteById(id);
        if (resultValue <= 0) {
            return DataResult.error("Meeting删除失败");
        }
        return DataResult.error("Meeting删除成功");
    }

    @Override
    public DataResult<Meeting> checkMeeting(CheckMeetingJson json) {
        Meeting meeting = meetingMapper.selectById(json.getId());
        if (meeting == null) {
            return DataResult.error("Meeting审核失败，ID:" + json.getId() + "不存在");
        }
        if (!dealCheckJson(meeting, json)) {
            return DataResult.error("审核格式不正确，错误result:" + json.getResult());
        }
        int resultValue = meetingMapper.updateById(meeting);
        if (resultValue <= 0) {
            return DataResult.error("Meeting审核更新失败");
        }
        return DataResult.ok("Meeting审核更新成功", meeting);
    }

    private String saveAttenderList(Meeting meeting) {
        if (meeting.getAttenderIdList() == null)
            return "";
        StringBuilder builder = new StringBuilder();
        meeting.setAttenderList(new ArrayList<>());
        for (Integer attenderId : meeting.getAttenderIdList()) {
            DataResult<User> attendResult = saveAttender(meeting.getId(), attenderId);
            if (attendResult.success()) {
                meeting.getAttenderList().add(attendResult.unwrap());
            } else {
                builder.append(attendResult.getMessage());
            }
        }
        return builder.toString();
    }

    private DataResult<User> saveAttender(Integer meetingId, Integer userId) {
        User user = userClient.getUserById(userId).unwrap();
        if (user == null) {
            return DataResult.error("User获取失败，ID:" + userId + "不存在");
        }
        Attendance attendance = new Attendance(meetingId, userId, user);
        int resultValue = attendanceMapper.insert(attendance);
        if (resultValue <= 0) {
            return DataResult.error(" *会议ID:" + meetingId + ", 参会人员ID:" + userId + "写入失败");
        }
        return DataResult.ok("会议参会人员写入成功", user);
    }

    private void removeAttenderList(Integer meetingId) {
        LambdaQueryWrapper<Attendance> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Attendance::getMeetingId, meetingId);
        int resultValue = attendanceMapper.delete(wrapper);
        if (resultValue < 0) {
            System.out.println("删除会议参会人员出错，Meeting ID:" + meetingId);
        }
    }

    private void getAttenderList(Meeting meeting) {
        LambdaQueryWrapper<Attendance> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Attendance::getMeetingId, meeting.getId());
        List<Attendance> attenders = attendanceMapper.selectList(wrapper);
        meeting.setAttenderIdList(new ArrayList<>());
        meeting.setAttenderList(new ArrayList<>());
        for (Attendance attender : attenders) {
            meeting.getAttenderIdList().add(attender.getUserId());
            User user = userClient.getUserById(attender.getUserId()).unwrap();
            meeting.getAttenderList().add(user);
        }
    }

    private List<Meeting> getCreateMeetingByUserId(Integer userId) {
        LambdaQueryWrapper<Meeting> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Meeting::getCreatorId, userId);
        List<Meeting> meetingList = meetingMapper.selectList(wrapper);
        meetingList.forEach(this::getAttenderList);
        return meetingList;
    }

    private List<Meeting> getAttendMeetingByUserId(Integer userId) {
        LambdaQueryWrapper<Attendance> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Attendance::getUserId, userId);
        List<Attendance> attendanceList = attendanceMapper.selectList(wrapper);
        List<Meeting> meetingList = new ArrayList<>();
        for (Attendance attendance : attendanceList) {
            meetingList.add(getMeetingById(attendance.getMeetingId()).unwrap());
        }
        return meetingList;
    }

    private boolean dealCheckJson(Meeting meeting, CheckMeetingJson json) {
        if (json.getResult().equals(CheckMeetingJson.PASS)) {
            meeting.setStatus(CHECK_PASS);
        } else if (json.getResult().equals(CheckMeetingJson.REJECT)) {
            meeting.setStatus(CHECK_REJECT);
        } else {
            return false;
        }
        return true;
    }
}
