package com.springoffice.check.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheckIn {
    public static final int UNCHECK = 0;
    public static final int CHECKED = 1;
    public static final int LATE = 2;
    public static final int FAILED = 3;

    @JsonProperty("check_id")
    private Integer checkId;
    @JsonProperty("user_id")
    private Integer userId;
    private Timestamp time;
    private Integer status;
    private String message;

    @TableField(exist = false)
    @JsonProperty("user_name")
    private String userName;
}
