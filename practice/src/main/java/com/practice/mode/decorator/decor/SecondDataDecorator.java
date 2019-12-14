package com.practice.mode.decorator.decor;

import com.practice.mode.decorator.loader.DefaultDataLoader;

/**
 * @ClassName DefaultDataDecorator
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/12/522:21
 */
public class SecondDataDecorator extends AbstractDefaultDataDecorator {
    public SecondDataDecorator(DefaultDataLoader abstractDefaultDataLoader) {
        super(abstractDefaultDataLoader);
    }

    @Override
    public void decorator() {
        System.out.println("4.second");
    }
}
