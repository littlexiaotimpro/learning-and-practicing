package com.practice.mode.template.method;

public class TemplateOne extends AbstractTemplate {
    @Override
    protected void start() {
        System.out.println(getClass().getSimpleName() + ".start");
    }

    @Override
    protected void first() {
        System.out.println(getClass().getSimpleName() + ".first");
    }

    @Override
    protected void second() {
        System.out.println(getClass().getSimpleName() + ".second");
    }

    @Override
    protected void third() {
        System.out.println(getClass().getSimpleName() + ".third");
    }

    @Override
    protected void end() {
        System.out.println(getClass().getSimpleName() + ".end");
    }
}
