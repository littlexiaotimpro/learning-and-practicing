package com.practice.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("Controller")
@RestController
@RequestMapping(value = "/swagger")
public class SwaggerController {

    @ApiOperation("测试接口")
    @GetMapping(value = "/test")
    public String res() {
        return "GET";
    }

}
