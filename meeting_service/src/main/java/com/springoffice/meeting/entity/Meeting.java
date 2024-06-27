package com.springoffice.meeting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Meeting {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @JsonProperty("creator_id")
    private Integer creatorId;
    @JsonProperty("dept_id")
    private Integer deptId;
    private String title;
    private String place;
    private Timestamp start;
    private Timestamp end;
    private Integer type;
    private String message;
    private Integer status;
    @JsonProperty("create_time")
    private Timestamp createTime;
}
