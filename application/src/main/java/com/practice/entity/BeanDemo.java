package com.practice.entity;

public class BeanDemo {

    private String demoCode;
    private String demoString;
    private Integer demoSize;
    private boolean demoBool;

    // 提供带参构造器，最好提供默认的无参构造器
    public BeanDemo() {
    }

    public BeanDemo(Integer demoSize) {
        this.demoSize = demoSize;
    }

    public void setDemoCode(String demoCode) {
        this.demoCode = demoCode;
    }

    public void setDemoString(String demoString) {
        this.demoString = demoString;
    }

    @Override
    public String toString() {
        return getClass()
                + " {demoCode=" + demoCode
                + ", demoString=" + demoString
                + ", demoSize=" + demoSize
                + ", demoBool=" + demoBool
                + "}";
    }
}
