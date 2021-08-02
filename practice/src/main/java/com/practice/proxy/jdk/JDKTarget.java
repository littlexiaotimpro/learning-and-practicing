package com.practice.proxy.jdk;

/**
 * CGLib 代理目标类
 */
public interface JDKTarget {

    Object invokeMethod(Object... objects);

}
