package com.practice.start.service.impl;

import com.practice.start.common.LocalCache;
import com.practice.start.entity.User;
import com.practice.start.service.AbstractLogicService;
import org.springframework.stereotype.Service;

@Service
public class LogicServiceImpl extends AbstractLogicService {

    @Override
    public void modifyCache() {
        // 每次操作会将原有实例的对象属性值覆盖，并没有达到缓存状态的作用？
        User user = LocalCache.getUser();
        System.out.println("modify -> " + user);
    }

    @Override
    public void checkCache() {
        // 因为是直接访问此接口，所以输出的结果和modify的执行结果不一致
        System.out.println("check -> " + LocalCache.getUser());
    }
}
