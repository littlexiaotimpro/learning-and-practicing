package com.practice.proxy.cglib;

import java.util.Arrays;

/**
 * CGLib 代理目标类
 */
public class CGLibTarget {

    public Object invokeMethod(Object... objects) {
        final String s = Arrays.toString(objects);
        System.out.println("Target -> " + s);
        return s;
    }

}
