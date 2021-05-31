package com.practice.mode.decorator.decor;

import com.practice.mode.decorator.loader.DefaultDataLoader;

public class SecondDataDecorator extends AbstractDefaultDataDecorator {
    public SecondDataDecorator(DefaultDataLoader abstractDefaultDataLoader) {
        super(abstractDefaultDataLoader);
    }

    @Override
    public void decorator() {
        System.out.println("4.second");
    }
}
