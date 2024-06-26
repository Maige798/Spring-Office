package com.springoffice.user.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Login {
    @TableId
    private Integer id;
    private String password;

    public Login(User user) {
        this.id = user.getId();
        this.password = user.getPassword();
    }
}
