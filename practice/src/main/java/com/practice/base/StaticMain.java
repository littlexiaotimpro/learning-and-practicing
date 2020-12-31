package com.practice.base;

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
    }

}
