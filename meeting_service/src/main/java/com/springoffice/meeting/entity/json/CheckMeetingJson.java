package com.springoffice.meeting.entity.json;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CheckMeetingJson {
    public static final int PASS = 0;
    public static final int REJECT = 1;

    private Integer id;
    private Integer result;
}
