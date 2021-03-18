package com.practice.thread.threadlocal;

import java.util.ArrayList;
import java.util.List;

public class Context {

    private static final ThreadLocal<List<Integer>> INTEGER_THREAD_LOCAL = ThreadLocal.withInitial(ArrayList::new);


    public static void setInteger(Integer v) {
        INTEGER_THREAD_LOCAL.get().add(v);
    }

    public static void removeInteger() {
        INTEGER_THREAD_LOCAL.get().clear();
    }

    public static List<Integer> getInteger() {
        return INTEGER_THREAD_LOCAL.get();
    }
}
