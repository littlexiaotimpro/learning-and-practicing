package com.practice.mode.decorator.decor;

import com.practice.mode.decorator.loader.DefaultDataLoader;

/**
 * @ClassName DefaultDataDecorator
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/12/522:21
 */
public abstract class AbstractDefaultDataDecorator implements DefaultDataLoader {

    private DefaultDataLoader defaultDataLoader;

    public AbstractDefaultDataDecorator(DefaultDataLoader defaultDataLoader) {
        this.defaultDataLoader = defaultDataLoader;
    }

    public abstract void decorator();

    @Override
    public void defaultLoad() {
        defaultDataLoader.defaultLoad();
        this.decorator();
    }
}
