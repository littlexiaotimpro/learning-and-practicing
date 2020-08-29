package com.practice.entity;

public class BeanDemoScope {

    private String code;

    public BeanDemoScope() {
        System.out.println("1.调用无参构造器！");
    }

    public void setCode(String code) {
        System.out.println("2.调用setter方法！");
        this.code = code;
    }

    public void initInstance() {
        System.out.println("3.调用实例初始化方法！");
    }

    public void useInstance() {
        System.out.println("4.1.使用实例！code = " + code);
    }

    public void destroyInstance() {
        System.out.println("5.调用实例销毁的方法！");
    }

}
