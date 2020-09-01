package com.practice.service.impl;

import com.practice.service.TargetProxy;

/**
 * 目标代理接口实现
 */
public class TargetProxyImpl implements TargetProxy {

    @Override
    public void process(String target, boolean status) {
        System.out.println("目标代理类方法！");
    }
}
