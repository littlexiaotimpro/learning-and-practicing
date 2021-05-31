package com.practice.mode.observer.self;

/**
 * 自定义订阅者类
 */
public interface Subscribe {

    /**
     * 添加或删除观察者
     */
    void addObserver(Observer o);

    void removeObserver(Observer o);

    void removeAllObservers();

    /**
     * 通知所有的观察者
     */
    void notifyAllObservers();

    void notifyAllObservers(Object arg);

}
