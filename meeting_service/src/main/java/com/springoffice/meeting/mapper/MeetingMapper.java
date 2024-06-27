package com.springoffice.meeting.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springoffice.meeting.entity.Meeting;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MeetingMapper extends BaseMapper<Meeting> {
}
