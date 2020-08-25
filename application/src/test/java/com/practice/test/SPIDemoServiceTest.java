package com.practice.test;

import com.practice.service.SPIDemoService;
import com.practice.service.impl.SPIDemoServiceImpl;
import org.junit.Test;

import java.util.ServiceLoader;

public class SPIDemoServiceTest {

    @Test
    public void testSPIDemo(){
        ServiceLoader<SPIDemoService> services = ServiceLoader.load(SPIDemoService.class);
        // 接口类型
        System.out.println(services.toString());
        // 遍历文件中配置的实现类
        for (SPIDemoService service : services) {
            System.out.println(service.getClass());
        }

        // 如果需要确认使用具体的哪一个实现类，则需要进行类型判断
        // 如，假设我需要使用默认实现
        for (SPIDemoService service : services) {
            if (service instanceof SPIDemoServiceImpl){
                service.process();
            }
        }
    }
}
