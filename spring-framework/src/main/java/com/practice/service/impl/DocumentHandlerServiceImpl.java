package com.practice.service.impl;

import com.practice.service.DocumentHandlerService;
import org.springframework.stereotype.Service;

/**
 * @author XiaoSi
 * @className DocumentHandlerServiceImpl
 * @description 文件处理接口实现
 * @date 2020/2/6
 */
@Service("documentInstance")
public class DocumentHandlerServiceImpl implements DocumentHandlerService {
    public String uploadExcel() {
        System.out.println("文件上传，待实现 ......");
        // 对于异常通知：若catch没有抛出其他异常信息，那么不会执行异常通知方法
//        try {
//            int a = 10 / 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        return "res";
    }
}
