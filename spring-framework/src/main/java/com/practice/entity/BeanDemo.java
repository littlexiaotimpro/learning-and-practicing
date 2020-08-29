package com.practice.entity;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class BeanDemo {

    private String demoCode;
    private String demoString;
    private Integer demoSize;
    private boolean demoBool;
    // 注入字符串集合属性
    private List<String> demoList;
    // 注入字符串集合属性
    private Map<String, String> demoMap;
    // 注入数组属性
    private String[] demoArray;
    // 注入对象属性
    private BeanDemoConnect connect;
    // 注入对象集合属性
    private List<BeanDemoConnect> connects;

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

    public void setDemoList(List<String> demoList) {
        this.demoList = demoList;
    }

    public void setDemoMap(Map<String, String> demoMap) {
        this.demoMap = demoMap;
    }

    public void setDemoArray(String[] demoArray) {
        this.demoArray = demoArray;
    }

    public void setConnect(BeanDemoConnect connect) {
        this.connect = connect;
    }

    public void setConnects(List<BeanDemoConnect> connects) {
        this.connects = connects;
    }

    public List<BeanDemoConnect> getConnects() {
        return connects;
    }

    /**
     * 级联赋值获取对象
     */
    public BeanDemoConnect getConnect() {
        return connect;
    }

    @Override
    public String toString() {
        return getClass()
                + " {demoCode=" + demoCode
                + ", demoString=" + demoString
                + ", demoSize=" + demoSize
                + ", demoBool=" + demoBool
                + ", demoList=" + demoList
                + ", demoMap=" + demoMap
                + ", demoArray=" + Arrays.toString(demoArray)
                + ", connect=" + connect
                + ", connects=" + connects
                + "}";
    }
}
