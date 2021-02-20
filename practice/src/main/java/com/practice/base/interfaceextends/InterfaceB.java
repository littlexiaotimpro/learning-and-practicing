package com.practice.base.interfaceextends;

public interface InterfaceB {
    void show();

    default void defaultFun(){
        System.out.println("B default function");
    }
}
