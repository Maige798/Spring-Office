package com.springoffice.book.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ConversationMessage {
    @JsonProperty("conversation_id")
    private Integer conversationId;
    private Integer order;
    private Timestamp time;
    @JsonProperty("sender_id")
    private Integer senderId;
    private String message;

    @TableField(exist = false)
    @JsonProperty("sender_name")
    private String senderName;
}
