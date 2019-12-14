package com.practice.mode.decorator.loader;

/**
 * @ClassName DataLoader
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/12/522:34
 */
public class DataLoader implements DefaultDataLoader {

    @Override
    public void defaultLoad() {
        System.out.println("1.loader");
    }
}
