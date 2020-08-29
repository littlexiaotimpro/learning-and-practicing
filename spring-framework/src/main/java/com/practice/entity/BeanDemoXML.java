package com.practice.entity;

public class BeanDemoXML {
    // 注入属性值，无需提供setter方法
    private BeanDemoConnect connect;

    // 提供带参构造器，最好提供默认的无参构造器
    public BeanDemoXML() {
    }

    public BeanDemoXML(BeanDemoConnect connect) {
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
