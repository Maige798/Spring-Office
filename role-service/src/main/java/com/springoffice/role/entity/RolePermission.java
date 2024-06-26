package com.springoffice.role.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@TableName("role_permission")
public class RolePermission {
    private Integer roleId;
    private Integer permissionId;
}
