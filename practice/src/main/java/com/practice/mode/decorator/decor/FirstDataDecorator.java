package com.practice.mode.decorator.decor;

import com.practice.mode.decorator.loader.DefaultDataLoader;

public class FirstDataDecorator extends AbstractDefaultDataDecorator {
    public FirstDataDecorator(DefaultDataLoader abstractDefaultDataLoader) {
        super(abstractDefaultDataLoader);
    }

    @Override
    public void decorator() {
        System.out.println("3.first");
    }
}
