package com.springoffice.meeting.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Attendance {
    @JsonProperty("meeting_id")
    private Integer meetingId;
    @JsonProperty("user_id")
    private Integer userId;

    @TableField(exist = false)
    private User user;
}
