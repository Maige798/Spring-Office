package com.springoffice.message.entity.json;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.springoffice.message.entity.Message;
import lombok.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SendMessageJson {
    private Integer origin;
    private List<Integer> targets;
    private Integer kind;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp time;
    private Integer checked;
    private String content;

    public List<Message> generateMessages() {
        if (this.targets == null) {
            return new ArrayList<>();
        }
        List<Message> list = new ArrayList<>();
        for (Integer target : targets) {
            list.add(new Message(origin, target, kind, time, checked, content));
        }
        return list;
    }
}
