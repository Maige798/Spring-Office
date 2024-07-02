package com.springoffice.check.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp start;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
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
    @TableField(exist = false)
    @JsonProperty("creator_name")
    private String creatorName;
}
