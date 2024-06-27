package com.springoffice.book.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Contact {
    @JsonProperty("user_id")
    private Integer userId;
    @JsonProperty("contact_id")
    private Integer contactId;
}
