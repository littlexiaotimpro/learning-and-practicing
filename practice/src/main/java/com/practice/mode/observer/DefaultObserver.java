package com.practice.mode.observer;

public class DefaultObserver extends AbstractObserver {

    public DefaultObserver(String tag) {
        super(tag);
    }

    @Override
    public void update(Object arg) {
        System.out.println("观察者【" + this.tag + "】响应变更... " + arg.toString());
    }
}
