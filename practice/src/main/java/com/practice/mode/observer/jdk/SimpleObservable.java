package com.practice.mode.observer.jdk;

import java.util.Observable;

/**
 * 简单的观察对象
 */
public class SimpleObservable extends Observable {

    private String context;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public void publish(String context) {
        if (!this.context.equals(context)) {
            System.out.println(this.context + " -> " + context);
            this.context = context;
            setChanged();
        }
        notifyObservers(this);
    }

    @Override
    public String toString() {
        return "{context=" + this.context + "}";
    }
}
