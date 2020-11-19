package com.practice.mode.template.method;

public abstract class AbstractTemplateMethod {

    public void process() {
        this.executor();
    }

    protected abstract void executor();

}
