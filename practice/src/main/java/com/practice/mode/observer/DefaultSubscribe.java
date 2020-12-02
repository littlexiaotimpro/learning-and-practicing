package com.practice.mode.observer;

public class DefaultSubscribe extends AbstractSubscribe {

    public DefaultSubscribe() {
    }

    public void publish(String s){
        change(s);
        notifyAllObservers(this);
    }

}
