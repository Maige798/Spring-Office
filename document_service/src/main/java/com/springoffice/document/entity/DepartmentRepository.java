package com.springoffice.document.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DepartmentRepository {
    @JsonProperty("dept_id")
    private Integer deptId;
    @JsonProperty("document_id")
    private Integer documentId;
}
