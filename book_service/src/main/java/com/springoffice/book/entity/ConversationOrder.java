package com.springoffice.book.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConversationOrder {
    @TableId
    @JsonProperty("conversation_id")
    private Integer conversationId;
    @JsonProperty("next_order")
    private Integer nextOrder;
}
