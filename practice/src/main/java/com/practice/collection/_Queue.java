package com.practice.collection;

import java.util.*;

/**
 * 队列
 */
public class _Queue {

    private final List<Integer> LIST_DATA = new ArrayList<>();

    {
        for (int i = 0; i < 10; i++) {
            LIST_DATA.add(i);
        }
    }

    private void setQueue() {
        List<Integer> tranList = new ArrayList<>(Arrays.asList(100, 200));
        Deque<Integer> tran = new LinkedList<>(tranList);
        int i = 0;
        while (!tran.isEmpty()) {
            Integer poll = tran.poll();
            System.out.println(poll);
            if (i < LIST_DATA.size()) {
                tran.offer(LIST_DATA.get(i));
                tranList.add(LIST_DATA.get(i++));
            }
        }
        System.out.println(tranList);
    }

    public static void main(String[] args) {
        _Queue queue = new _Queue();
        queue.setQueue();
    }

}
