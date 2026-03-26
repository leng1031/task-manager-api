package com.xuleng.taskmanager.exception;

import com.xuleng.taskmanager.common.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(TaskNotFoundException.class)
    public ApiResponse<String> handleNoutFound(TaskNotFoundException e){
        return new ApiResponse<>(404,e.getMessage(),null);
    }

    @ExceptionHandler(Exception.class)
    public ApiResponse<String> handleOther(Exception e){
        return new ApiResponse<>(500,"Internal error", null);
    }
}
