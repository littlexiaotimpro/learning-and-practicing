package com.practice.base;

/**
 * 父类
 */
public class SuperClass {

    static {
        System.out.println("父类静态代码块");
    }

    {
        System.out.println("父类代码块");
    }

    public SuperClass() {
        show();
        System.out.println("父类构造器");
    }

    public void show(){
        System.out.println("父类普通方法");
    }

}
