package com.springoffice.user.entity.json;

import com.springoffice.user.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserStatusJson {
    private Integer id;
    private Integer status;

    public void update(User user) {
        if (!user.getId().equals(this.id)) {
            System.err.println("UserStatusJson -- User ID不匹配，无法更新");
            return;
        }
        user.setStatus(status);
    }
}
