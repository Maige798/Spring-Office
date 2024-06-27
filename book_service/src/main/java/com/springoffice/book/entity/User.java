package com.springoffice.book.entity;

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
    private Timestamp createTime;
    private Integer status;
    @JsonProperty("role_id")
    private Integer roleId;
    @JsonProperty("dept_id")
    private Integer deptId;
}
