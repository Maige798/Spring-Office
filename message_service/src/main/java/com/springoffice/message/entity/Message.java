package com.springoffice.message.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Message {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private Integer origin;
    private Integer target;
    private Integer kind;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp time;
    private Integer checked;
    private String content;

    public Message(Integer origin, Integer target, Integer kind, Timestamp time, Integer checked, String content) {
        this.origin = origin;
        this.target = target;
        this.kind = kind;
        this.time = time;
        this.checked = checked;
        this.content = content;
    }
}
