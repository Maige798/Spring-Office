package com.springoffice.fish.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Fish {
    @TableId
    @JsonProperty("user_id")
    private Integer userId;
    private Integer number;

    @TableField(exist = false)
    @JsonProperty("user_name")
    private String userName;
}
