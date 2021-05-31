package com.practice.mode.decorator.decor;

import com.practice.mode.decorator.loader.DefaultDataLoader;

/**
 * 实现装饰对象的抽象类，提供装饰方法供子类实现
 */
public abstract class AbstractDefaultDataDecorator implements DefaultDataLoader {

    private final DefaultDataLoader defaultDataLoader;

    public AbstractDefaultDataDecorator(DefaultDataLoader defaultDataLoader) {
        this.defaultDataLoader = defaultDataLoader;
    }

    /**
     * 由子类实现的装饰方法
     */
    public abstract void decorator();

    @Override
    public void defaultLoad() {
        defaultDataLoader.defaultLoad();
        this.decorator();
    }
}
