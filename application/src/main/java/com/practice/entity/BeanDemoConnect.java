package com.practice.entity;

/**
 * @description BeanDemo的注入对象
 */
public class BeanDemoConnect {
    private String connectCode;
    private String connectString;

    public void setConnectCode(String connectCode) {
        this.connectCode = connectCode;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    @Override
    public String toString() {
        return getClass() + "{connectCode=" + connectCode + ", connectString=" + connectString + "}";
    }
}
