package com.practice.test.proxy;

import com.practice.proxy.CGLibProxy;
import org.junit.Test;

public class CGLibProxyTest {

    @Test
    public void testJDKProxy(){
        CGLibProxy cgLibProxy = new CGLibProxy();
        cgLibProxy.process();
    }
}
