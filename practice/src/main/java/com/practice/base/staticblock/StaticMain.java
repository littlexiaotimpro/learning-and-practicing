package com.practice.base.staticblock;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StaticMain {

    private static String str;
    private static List<String> list;

    private static void init(){
        str = Optional.ofNullable(str).orElse("str");
        list = Optional.ofNullable(list).orElse(new ArrayList<>(1));
        list.add("list");
    }


    public static void main(String[] args) {
        init();
        System.out.println(str);
        System.out.println(list);

        // 1.static 修饰符执行顺序
        System.out.println("----------------------------------");
        System.out.println("-------1.static 修饰符执行顺序");
        SuperClass superClass = new SubClass();
        System.out.println(superClass);

        // 2.静态内部类
        System.out.println("----------------------------------");
        System.out.println("-------2.静态内部类");
        StaticClass.InnerClass innerClass = new StaticClass.InnerClass();
        System.out.println(innerClass.showMessage());
        StaticClazz staticClazz = new StaticClazz();
        System.out.println(staticClazz.showMessage());
    }

}
