package com.practice.start.common;

import com.practice.start.entity.User;

public abstract class LocalCache {

    private static final ThreadLocal<User> cache = new ThreadLocal<>();

    public static User getUser(){
        return cache.get();
    }

    public static void setUser(User user) {
        cache.set(user);
    }

    public static void clear() {
        cache.remove();
    }
}
