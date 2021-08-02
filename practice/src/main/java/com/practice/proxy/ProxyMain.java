package com.practice.proxy;

import com.practice.proxy.cglib.CGLibProxy;
import com.practice.proxy.jdk.JDKProxy;
import com.practice.proxy.jdk.JDKTarget;
import com.practice.proxy.jdk.JDKTargetImpl;

public class ProxyMain {

    public static void main(String[] args) {
        final CGLibProxy cgLibProxy = new CGLibProxy();
        cgLibProxy.process();

        JDKTarget jdkTarget = new JDKTargetImpl();
        final JDKProxy jdkProxy = new JDKProxy(jdkTarget);
        jdkProxy.process();
    }

}
