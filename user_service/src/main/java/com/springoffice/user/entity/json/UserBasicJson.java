package com.springoffice.user.entity.json;

import com.springoffice.user.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserBasicJson {
    private Integer id;
    private String name;
    private String photo;
    private Integer sex;
    private String phone;
    private String email;

    public void update(User user) {
        if (!user.getId().equals(this.id)) {
            System.err.println("UserBasicJson -- User ID不匹配，无法更新");
            return;
        }
        user.setName(this.name);
        user.setPhoto(this.photo);
        user.setSex(this.sex);
        user.setPhone(this.phone);
        user.setEmail(this.email);
    }
}
