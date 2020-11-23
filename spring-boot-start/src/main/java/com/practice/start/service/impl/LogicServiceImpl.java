package com.practice.start.service.impl;

import com.practice.start.service.AbstractLogicService;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(value = "prototype")
public class LogicServiceImpl extends AbstractLogicService {

    @Override
    public void modifyCache(String username) {
        // 每次操作会将原有实例的对象属性值覆盖，并没有达到缓存状态的作用
        localCache.setId(username);
        localCache.put(username,username);
    }

    @Override
    public void checkCache() {
        System.out.println(localCache.getId());
        System.out.println(localCache.getCache());
    }
}
