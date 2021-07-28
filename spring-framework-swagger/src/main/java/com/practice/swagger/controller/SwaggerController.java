package com.practice.swagger.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api("Controller")
@RestController
@RequestMapping(value = "/swagger")
public class SwaggerController {

    private final Logger logger = LoggerFactory.getLogger(SwaggerController.class);

    @ApiOperation("GET 测试")
    @GetMapping(value = "/get")
    public Object get(HttpServletRequest request, HttpServletResponse response) {
        logger.info("GET -> {}:{}", response.getStatus(), request.getPathInfo());
        return "GET";
    }

    @ApiOperation("HEAD 测试")
    @RequestMapping(value = "/head", method = RequestMethod.HEAD)
    public Object head(HttpServletRequest request, HttpServletResponse response) {
        logger.info("HEAD -> {}:{}", response.getStatus(), request.getPathInfo());
        return "HEAD";
    }

    @ApiOperation("OPTIONS 测试")
    @RequestMapping(value = "/options", method = RequestMethod.OPTIONS)
    public Object options(HttpServletRequest request, HttpServletResponse response) {
        logger.info("OPTIONS -> {}:{}", response.getStatus(), request.getPathInfo());
        return "OPTIONS";
    }

    @ApiOperation("TRACE 测试")
    @RequestMapping(value = "/trace", method = RequestMethod.TRACE)
    public Object trace(HttpServletRequest request, HttpServletResponse response) {
        logger.info("TRACE -> {}:{}", response.getStatus(), request.getPathInfo());
        return "TRACE";
    }

    @ApiOperation("POST 测试")
    @PostMapping(value = "/post")
    public Object post(HttpServletRequest request, HttpServletResponse response) {
        logger.info("POST -> {}:{}", response.getStatus(), request.getPathInfo());
        return "POST";
    }

    @ApiOperation("PUT 测试")
    @PutMapping(value = "/put")
    public Object put(HttpServletRequest request, HttpServletResponse response) {
        logger.info("PUT -> {}:{}", response.getStatus(), request.getPathInfo());
        return "PUT";
    }

    @ApiOperation("DELETE 测试")
    @DeleteMapping(value = "/delete")
    public Object delete(HttpServletRequest request, HttpServletResponse response) {
        logger.info("DELETE -> {}:{}", response.getStatus(), request.getPathInfo());
        return "DELETE";
    }

    @ApiOperation("PATCH 测试")
    @PatchMapping(value = "/patch")
    public Object patch(HttpServletRequest request, HttpServletResponse response) {
        logger.info("PATCH -> {}:{}", response.getStatus(), request.getPathInfo());
        return "PATCH";
    }

}
