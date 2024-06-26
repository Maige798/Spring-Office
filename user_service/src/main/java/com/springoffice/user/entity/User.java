package com.springoffice.user.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Date;
import java.sql.Timestamp;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @JsonProperty("is_admin")
    private Integer isAdmin;
    private String photo;
    private Integer sex;
    private String phone;
    private String email;
    @JsonProperty("hire_date")
    private Date hireDate;
    @JsonProperty("create_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    private Integer status;
    @JsonProperty("role_id")
    private Integer roleId;
    @JsonProperty("dept_id")
    private Integer deptId;

    @TableField(exist = false)
    @JsonProperty("password")
    private String password;
    @TableField(exist = false)
    @JsonProperty("dept_name")
    private String deptName;
    @TableField(exist = false)
    @JsonProperty("dept_message")
    private String deptMessage;
    @TableField(exist = false)
    @JsonProperty("role_name")
    private String roleName;

    public User(String name, Integer sex, String phone, String email, Integer status, Integer roleId, Integer deptId) {
        this.name = name;
        this.sex = sex;
        this.phone = phone;
        this.email = email;
        this.status = status;
        this.roleId = roleId;
        this.deptId = deptId;
    }
}
