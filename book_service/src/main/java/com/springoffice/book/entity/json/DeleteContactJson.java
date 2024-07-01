package com.springoffice.book.entity.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DeleteContactJson {
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("contact_id")
    private Integer contactId;
}
