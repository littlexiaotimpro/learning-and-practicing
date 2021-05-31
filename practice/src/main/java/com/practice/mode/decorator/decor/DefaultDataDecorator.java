package com.practice.mode.decorator.decor;

import com.practice.mode.decorator.loader.DefaultDataLoader;

public class DefaultDataDecorator extends AbstractDefaultDataDecorator {
    public DefaultDataDecorator(DefaultDataLoader abstractDefaultDataLoader) {
        super(abstractDefaultDataLoader);
    }

    @Override
    public void decorator() {
        System.out.println("2.default");
    }
}
