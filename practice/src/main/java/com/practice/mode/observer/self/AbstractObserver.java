package com.practice.mode.observer.self;

public abstract class AbstractObserver implements Observer{
    public String tag;

    protected AbstractObserver(String tag){
        this.tag = tag;
    }

}
