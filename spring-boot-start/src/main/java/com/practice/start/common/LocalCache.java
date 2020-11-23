package com.practice.start.common;

import java.util.HashMap;
import java.util.Map;

public final class LocalCache {
    private String id;
    private final Map<String, Object> cache = new HashMap<>();

    public LocalCache() {
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Map<String, Object> getCache() {
        return cache;
    }

    public <T> T get(String o, Class<T> aClass) {
        Object o1 = cache.get(o);
        return aClass.cast(o1);
    }

    public void put(String o, Object o1) {
        cache.put(o, o1);
    }

    public void evict(String o) {
        cache.remove(o);
    }

    public void clear() {
        cache.clear();
    }
}
