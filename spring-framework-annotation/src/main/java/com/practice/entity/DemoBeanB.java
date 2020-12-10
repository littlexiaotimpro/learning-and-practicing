package com.practice.entity;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class DemoBeanB implements InitializingBean, DisposableBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        // 先于指定的 init-method 方法前执行
        System.out.println("初始化方法 === InitializingBean.afterPropertiesSet =====>" + getClass().getSimpleName());
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("销毁方法 === DisposableBean.destroy =====>" + getClass().getSimpleName());
    }
}
