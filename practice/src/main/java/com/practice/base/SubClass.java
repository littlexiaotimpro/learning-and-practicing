package com.practice.base;

/**
 * 子类
 */
public class SubClass extends SuperClass {

    static {
        System.out.println("子类静态代码块");
    }

    {
        System.out.println("子类代码块");
    }

    public SubClass(){
        System.out.println("子类构造器");
    }

    @Override
    public void show() {
        System.out.println("子类普通方法");
    }
}
