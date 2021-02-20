package com.practice.base.interfaceextends;

public interface InterfaceA {

    void show();

    default void defaultFun(){
        System.out.println("A default function");
    }

}
