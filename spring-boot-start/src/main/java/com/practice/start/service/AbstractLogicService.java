package com.practice.start.service;

import com.practice.start.common.LocalCache;

public abstract class AbstractLogicService implements LogicService {

    protected LocalCache localCache;

    public AbstractLogicService() {
        this.localCache = new LocalCache();
    }
}
