package com.practice.mode.observer.self;

import java.util.Objects;
import java.util.Vector;

public abstract class AbstractSubscribe implements Subscribe {

    // 通知内容
    private String context;
    // 观察者
    private final Vector<Observer> observers;
    // 内容是否变更
    private boolean changed = false;

    public AbstractSubscribe() {
        this.observers = new Vector<>();
    }

    protected void change(String context) {
        this.changed = !Objects.equals(this.context, context);
        this.context = context;
    }

    @Override
    public void addObserver(Observer o) {
        if (this.observers.contains(o)) {
            return;
        }
        this.observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        this.observers.remove(o);
    }

    @Override
    public void removeAllObservers() {
        this.observers.clear();
    }

    @Override
    public void notifyAllObservers() {
        notifyAllObservers(null);
    }

    @Override
    public void notifyAllObservers(Object arg) {
        if (!changed) {
            return;
        }
        resetChanged();
        for (Observer observer : this.observers) {
            observer.update(arg);
        }
    }

    private void resetChanged(){
        this.changed = false;
    }

    @Override
    public String toString() {
        return "{context=" + this.context + "}";
    }
}
