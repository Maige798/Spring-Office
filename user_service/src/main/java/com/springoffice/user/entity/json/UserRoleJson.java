package com.springoffice.user.entity.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springoffice.user.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserRoleJson {
    private Integer id;
    @JsonProperty("role_id")
    private Integer roleId;

    public void update(User user) {
        if (!user.getId().equals(this.id)) {
            System.err.println("UserStatusJson -- User ID不匹配，无法更新");
            return;
        }
        user.setRoleId(this.roleId);
    }
}
