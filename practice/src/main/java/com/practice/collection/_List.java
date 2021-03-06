package com.practice.collection;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
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
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);

        List<Integer> list1 = new ArrayList<>(2);
        list1.add(1);
        list1.add(2);
        // grow
        list1.add(3);

        List<Integer> list2 = new ArrayList<>(0);
        list2.add(1);

        List<Integer> list3 = new ArrayList<>(list);
        list3.add(1);

        List<Integer> list4 = new ArrayList<>(new ArrayList<>());
        list4.add(1);

        System.out.println("list: " + list);
        System.out.println("list1: " + list1);
        System.out.println("list2: " + list2);
        System.out.println("list3: " + list3);
        System.out.println("list4: " + list4);
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

    private void subList() {
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(100);
        list.add(list.size() - 1, 6);
        for (int i = 0; i < list.size() - 1; i++) {
            System.out.println(Arrays.toString(list.subList(i, i == list.size() - 1 ? list.size() : i + 2).toArray()));
        }
    }

    /**
     * 使用 foreach 删除元素会抛异常
     * 原因：
     * 在执行 remove 操作时 modCount 自增，而 expectedModCount 为变化，导致二者不同
     * <p>
     * Iterator.remove
     * 在执行 remove 后，会将 modCount 的值赋予 expectedModCount
     */
    private void remove() {
        List<String> list = new ArrayList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        // list.removeIf("3"::equals);
        // modCount != expectedModCount
        for (String str : list) {
            if ("3".equals(str)) {
                list.remove(str);
            }
        }
        System.out.println(list);
    }

    public static void main(String[] args) {
        instance = getInstance();
//        instance.arrayList();
//        instance.linkedList();
//        instance.subList();
//        instance.remove();
    }
}
