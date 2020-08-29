package com.practice.entity;

public class BeanDemoAnnotation {
    // 注入对象属性
    private BeanDemoConnect connect;

    // 提供带参构造器，最好提供默认的无参构造器
    public BeanDemoAnnotation() {
    }

    public BeanDemoAnnotation(BeanDemoConnect connect) {
        this.connect = connect;
    }

    public void setConnect(BeanDemoConnect connect) {
        this.connect = connect;
    }

    /**
     * 级联赋值获取对象
     */
    public BeanDemoConnect getConnect() {
        return connect;
    }

    @Override
    public String toString() {
        return getClass() + " {connect=" + connect + "}";
    }
}
