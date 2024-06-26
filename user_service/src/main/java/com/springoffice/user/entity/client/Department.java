package com.springoffice.user.entity.client;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.springoffice.user.entity.User;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Department {
    private Integer id;
    private String name;
    @JsonProperty("master_id")
    private Integer masterId;
    private String message;

    public Department(User user) {
        this.name = user.getDeptName();
        this.message = user.getDeptMessage();
        this.masterId = user.getId();
    }
}
