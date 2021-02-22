package com.practice.base.staticblock;

/**
 * 父类
 */
public class SuperClass {

    static {
        System.out.println("1.父类静态代码块");
    }

    {
        System.out.println("3.父类代码块");
    }

    public SuperClass() {
        /*
         * 子类重写了父类的方法，此处调用的为子类的方法
         */
        show();
        superShow();
        System.out.println("4.父类构造器");
    }

    public void show() {
        System.out.println("父类被子类重写普通方法");
    }

    public void superShow() {
        System.out.println("父类普通方法");
    }

}
