package com.springoffice.book.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Conversation {
    @TableId(type = IdType.AUTO)
    private Integer id;
    @JsonProperty("a_id")
    private Integer aId;
    @JsonProperty("b_id")
    private Integer bId;

    @TableField(exist = false)
    @JsonProperty("messages")
    private List<ConversationMessage> conversationMessages;
    @TableField(exist = false)
    @JsonProperty("a_name")
    private String aName;
    @TableField(exist = false)
    @JsonProperty("b_name")
    private String bName;
}
