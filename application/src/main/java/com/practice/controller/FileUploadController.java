package com.practice.controller;

import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

/**
 * @author XiaoSi
 * @className FileUploadController
 * @description 文件上传
 * @date 2020/2/6
 */
@RestController
@RequestMapping("/file")
public class FileUploadController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/upload")
    @ApiOperation("文件上传")
    public Object uploadFile(StandardMultipartHttpServletRequest request){
        logger.info("console request");
        return request;
    }

}
