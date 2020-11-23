package com.practice.start.service.impl;

import com.practice.start.common.LocalCache;
import com.practice.start.entity.User;
import com.practice.start.service.AbstractLogicService;
import org.springframework.stereotype.Service;

@Service
public class LogicServiceImpl extends AbstractLogicService {

    @Override
    public void modifyCache(String username) {
        // 每次操作会将原有实例的对象属性值覆盖，并没有达到缓存状态的作用？
        User user = new User();
        user.setUsername(username);
        LocalCache.setUser(user);
    }

    @Override
    public void checkCache() {
        System.out.println(LocalCache.getUser().getUsername());
    }
}
