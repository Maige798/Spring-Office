package com.springoffice.role.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @JsonProperty("dept_id")
    private Integer deptId;

    @TableField(exist = false)
    private List<Permission> permissions;
}
