package com.springoffice.global.util;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DataResult<D> {
    public static final int OK = 200;
    public static final int ERROR = 400;
    public static final int SYSTEM_ERROR = 500;
    public static final int BUSY = 600;
    private static final String STATUS_MESSAGE = "--DataResult--Status Code:";

    private Integer code;
    private String message;
    private D data;

    public D unwrap() {
        if (code != 200) showStatus();
        if (data == null) {
            System.err.println(this + ".data的值为null!");
        }
        return data;
    }

    public void showStatus() {
        switch (this.code) {
            case OK:
                System.out.println(STATUS_MESSAGE + "OK");
                break;
            case ERROR:
                System.out.println(STATUS_MESSAGE + "ERROR");
                break;
            case SYSTEM_ERROR:
                System.out.println(STATUS_MESSAGE + "SYSTEM ERROR");
                break;
            case BUSY:
                System.out.println(STATUS_MESSAGE + "BUSY");
                break;
            default:
                System.out.println("--DataResult--Unexpected Code:" + this.code);
        }
    }

    public boolean success() {
        return code.equals(200);
    }

    public static <D> DataResult<D> ok(String message, D data) {
        return new DataResult<>(200, message, data);
    }

    public static <D> DataResult<D> ok(String message) {
        return new DataResult<>(200, message, null);
    }

    public static <D> DataResult<D> error(String message, D data) {
        return new DataResult<>(400, message, data);
    }

    public static <D> DataResult<D> error(String message) {
        return new DataResult<>(400, message, null);
    }
}
