package com.practice.test.proxy;

import com.practice.proxy.JDKProxy;
import com.practice.service.TargetProxy;
import com.practice.service.impl.TargetProxyImpl;
import org.junit.Test;

public class JDKProxyTest {

    @Test
    public void testJDKProxy(){
        TargetProxy targetProxy = new TargetProxyImpl();
        JDKProxy jdkProxy = new JDKProxy(targetProxy);
        jdkProxy.process();
    }
}
