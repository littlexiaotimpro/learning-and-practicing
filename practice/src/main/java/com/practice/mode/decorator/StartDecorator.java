package com.practice.mode.decorator;

import com.practice.mode.decorator.decor.AbstractDefaultDataDecorator;
import com.practice.mode.decorator.decor.DefaultDataDecorator;
import com.practice.mode.decorator.decor.FirstDataDecorator;
import com.practice.mode.decorator.decor.SecondDataDecorator;
import com.practice.mode.decorator.loader.DataLoader;

public class StartDecorator {

    /**
     * 装饰方法的执行顺序，在于用户创建对象时的包装顺序
     */
    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        // 0.对 DataLoader 对象进行默认装饰包装
        AbstractDefaultDataDecorator decorator = new DefaultDataDecorator(loader);
        // 1.对 DefaultDataDecorator 对象进行包装
        decorator = new FirstDataDecorator(decorator);
        // 2.对 FirstDataDecorator 对象进行包装
        decorator = new SecondDataDecorator(decorator);
        /*
        * 调用结果输出顺序：
        * DataLoader::defaultLoad
        * -> DefaultDataDecorator::decorator
        * -> FirstDataDecorator::decorator
        * -> SecondDataDecorator::decorator
        */
        decorator.defaultLoad();
    }
}
