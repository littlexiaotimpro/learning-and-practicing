package com.practice.proxy.jdk;

import java.util.Arrays;

public class JDKTargetImpl implements JDKTarget {

    @Override
    public Object invokeMethod(Object... objects) {
        final String s = Arrays.toString(objects);
        System.out.println("Target -> " + s);
        return s;
    }
}
