package com.springoffice.meeting.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;
import java.util.List;

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

    @TableField(exist = false)
    private User creator;
    @TableField(exist = false)
    @JsonProperty("attender_ids")
    private List<Integer> attenderIdList;
    @TableField(exist = false)
    @JsonProperty("attenders")
    private List<User> attenderList;
}
