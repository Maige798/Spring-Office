package com.springoffice.global.util;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DataResult<D> {
    private Integer code;
    private String message;
    private D data;

    public D unwrap() {
        return data;
    }

    public boolean success() {
        return code.equals(200);
    }

    public static <D> DataResult<D> ok(String message, D data) {
        return new DataResult<>(200, message, data);
    }

    public static <D> DataResult<D> error(String message, D data) {
        return new DataResult<>(400, message, data);
    }
}
