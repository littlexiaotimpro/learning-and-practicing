package com.practice.mode.observer.jdk;

import java.util.Observable;
import java.util.Observer;

public class SimpleObserver implements Observer {

    private String tag;

    public SimpleObserver() {
    }

    public SimpleObserver(String tag) {
        this.tag = tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println("简单观察者【" + tag + "】察觉变更，并作出响应... " + arg.toString());
    }
}
