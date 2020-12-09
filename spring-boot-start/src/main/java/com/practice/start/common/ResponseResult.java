package com.practice.start.common;

import lombok.Data;

@Data
public class ResponseResult {
    private String code;
    private String message;

    public static ResponseResult result(String message){
        ResponseResult result = new ResponseResult();
        result.setCode("-1");
        result.setMessage(message);
        return result;
    }

    public static ResponseResult result(String code,String message){
        ResponseResult result = new ResponseResult();
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

}
