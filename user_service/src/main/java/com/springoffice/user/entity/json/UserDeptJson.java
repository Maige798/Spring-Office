package com.springoffice.user.entity.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springoffice.user.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDeptJson {
    private Integer id;
    @JsonProperty("dept_id")
    private Integer deptId;

    public void update(User user) {
        if (!user.getId().equals(this.id)) {
            System.err.println("UserStatusJson -- User ID不匹配，无法更新");
            return;
        }
        user.setDeptId(this.deptId);
    }
}
