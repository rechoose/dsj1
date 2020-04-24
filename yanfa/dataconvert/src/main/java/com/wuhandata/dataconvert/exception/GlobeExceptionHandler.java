package com.wuhandata.dataconvert.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理类
 */
//@RestControllerAdvice
public class GlobeExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseResult nullPointerExceptionHandler(Exception ex) {
        ex.printStackTrace();
        return ResponseResult.fale(ex.getMessage());
    }

    public static void main(String[] args) {
        System.out.println("hello");
    }
}
