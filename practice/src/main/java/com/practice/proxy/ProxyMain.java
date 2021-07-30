package com.practice.proxy;

import com.practice.proxy.cglib.CGLibProxy;

public class ProxyMain {

    public static void main(String[] args) {
        final CGLibProxy cgLibProxy = new CGLibProxy();
        cgLibProxy.process();
    }

}
