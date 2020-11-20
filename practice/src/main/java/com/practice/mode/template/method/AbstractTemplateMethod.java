package com.practice.mode.template.method;

public abstract class AbstractTemplateMethod {

    protected String common;

    {
        this.common = "代码块赋值";
        System.out.println("抽象父类的代码块");
    }

    public AbstractTemplateMethod() {
        System.out.println("抽象父类的构造器");
    }

    public void process() {
        this.executor();
    }

    protected abstract void executor();

}
