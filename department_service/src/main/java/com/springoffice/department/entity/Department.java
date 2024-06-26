package com.springoffice.department.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Department {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    @JsonProperty("master_id")
    private Integer masterId;
    private String message;
}
