package com.practice.base.staticblock;

/**
 * 子类
 */
public class SubClass extends SuperClass {

    static {
        System.out.println("2.子类静态代码块");
    }

    {
        System.out.println("5.子类代码块");
    }

    public SubClass(){
        System.out.println("6.子类构造器");
    }

    @Override
    public void show() {
        System.out.println("子类重写父类的普通方法");
    }
}
