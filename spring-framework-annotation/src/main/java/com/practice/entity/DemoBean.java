package com.practice.entity;

public class DemoBean {

    /**
     * 初始化方法
     */
    public void init(){
        System.out.println("初始化方法 === Bean.initMethod =====>" + getClass().getSimpleName());
    }

    /**
     * 销毁
     */
    public void destroy(){
        System.out.println("销毁方法 === Bean.destroyMethod =====>" + getClass().getSimpleName());
    }

}
