package com.springoffice.global.util;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectExceptionAdvice {
    @ExceptionHandler(Exception.class)
    public DataResult<String> exceptionHandler(Exception e) {
        // todo
        e.printStackTrace();
        return new DataResult<>(500, "系统错误", e.getMessage());
    }
}
