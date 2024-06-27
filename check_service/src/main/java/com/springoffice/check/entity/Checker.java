package com.springoffice.check.entity;

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
public class Checker {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @JsonProperty("creator_id")
    private Integer creatorId;
    @JsonProperty("dept_id")
    private Integer deptId;
    private Timestamp start;
    private Timestamp end;
    private String message;

    @TableField(exist = false)
    @JsonProperty("members")
    private List<Integer> memberIds;
    @TableField(exist = false)
    @JsonProperty("check_in_list")
    private List<CheckIn> checkInList;
    @TableField(exist = false)
    @JsonProperty("attendance_rate")
    private Double attendanceRate;
}
