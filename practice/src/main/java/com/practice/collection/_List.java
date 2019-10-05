package com.practice.collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName _List
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/10/514:37
 */
public class _List {

    private static _List instance;

    private _List() {
    }

    /**
     * ArrayList
     */
    private void arrayList() {
        List list = new ArrayList();
        System.out.println("ArrayList");
    }

    /**
     * LinkedList
     */
    private void linkedList() {
        List list = new LinkedList();
        System.out.println("LinkedList");
    }

    /**
     * 单例模式下的返回唯一实例
     *
     * @return
     */
    private static _List getInstance() {
        if (instance != null) {
            return instance;
        }
        return new _List();
    }

    public static void main(String[] args) {
        instance = getInstance();
        instance.arrayList();
        instance.linkedList();
    }
}
