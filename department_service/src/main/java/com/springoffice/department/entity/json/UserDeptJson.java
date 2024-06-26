package com.springoffice.department.entity.json;

import com.fasterxml.jackson.annotation.JsonProperty;
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
}
