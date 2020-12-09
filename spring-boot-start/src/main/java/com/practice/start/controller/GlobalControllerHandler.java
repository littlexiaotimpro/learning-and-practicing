package com.practice.start.controller;

import com.practice.start.common.ResponseResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalControllerHandler {

    @ExceptionHandler(value = {NullPointerException.class})
    @ResponseBody
    public ResponseResult handleException(HttpServletRequest request, HttpServletResponse response, NullPointerException e) {
        return ResponseResult.result(String.valueOf(response.getStatus()), e.getMessage());
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseBody
    public ResponseResult handleException(HttpServletRequest request, HttpServletResponse response, Exception e) {
        return ResponseResult.result(e.getMessage());
    }

}
