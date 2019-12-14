package com.practice.mode.decorator;

import com.practice.mode.decorator.decor.AbstractDefaultDataDecorator;
import com.practice.mode.decorator.decor.DefaultDataDecorator;
import com.practice.mode.decorator.decor.FirstDataDecorator;
import com.practice.mode.decorator.decor.SecondDataDecorator;
import com.practice.mode.decorator.loader.DataLoader;

/**
 * @ClassName StartDecorator
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/12/521:57
 */
public class StartDecorator {

    public static void main(String[] args) {
        DataLoader loader = new DataLoader();
        AbstractDefaultDataDecorator decorator = new DefaultDataDecorator(loader);
        decorator = new FirstDataDecorator(decorator);
        decorator = new SecondDataDecorator(decorator);
        decorator.defaultLoad();
    }
}
