package com.springoffice.department.entity.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChangeMemberJson {
    @JsonProperty("dept_id")
    private Integer deptId;
    @JsonProperty("member_id")
    private Integer memberId;
}
