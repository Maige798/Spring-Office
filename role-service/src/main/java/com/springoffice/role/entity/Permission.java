package com.springoffice.role.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Permission {
    @TableId
    private Integer id;
    private String name;
}
