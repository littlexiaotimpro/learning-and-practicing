package com.practice.base.interfaceextends;

public interface InterfaceC extends InterfaceA, InterfaceB {
    @Override
    default void defaultFun(){
        System.out.println("C default function");
    }
}
