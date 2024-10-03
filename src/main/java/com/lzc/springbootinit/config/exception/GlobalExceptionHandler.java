package com.lzc.springbootinit.config.exception;

import com.lzc.springbootinit.pojo.ResponseVo;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseVo exceptionHandle(Exception exception) {
        exception.printStackTrace();
        return ResponseVo.error("操作失败");
    }


}
