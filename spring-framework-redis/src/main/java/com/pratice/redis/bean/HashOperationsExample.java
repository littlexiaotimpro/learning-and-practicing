package com.pratice.redis.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Supplier;

/**
 * HashOperations
 *
 * @date 2021/12/5
 * @see org.springframework.data.redis.core.HashOperations
 */
public class HashOperationsExample {

    // 随机的用户ID
    public final static String USER_KEY = "USER:";

    private final Map<String, User> modelData;

    {
        modelData = new HashMap<>();
        Supplier<User> supplier = User::new;
        for (int i = 1; i <= 5; i++) {
            User user = supplier.get();
            user.setName("name" + i);
            // M:1, W:0
            user.setGender((i & 1) == 0 ? 0 : 1);
            user.setAge((int) (Math.random() * 100));
            modelData.put("N_" + i, user);
        }
    }

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Resource(name = "redisTemplate")
    private HashOperations<String, String, Object> hashOps;

    public void initValue() throws IllegalAccessException {
        Cache cache = new Cache();
        cache.setNames(new HashSet<>());
        for (Map.Entry<String, User> userEntry : modelData.entrySet()) {
            String key = String.format("%s%s", USER_KEY, userEntry.getKey());
            User user = userEntry.getValue();
            Field[] fields = User.class.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                hashOps.put(key, field.getName(), field.get(user));
            }
            cache.setUser(user);
            cache.getNames().add(user.name);
        }
        String key = String.format("%s%s", USER_KEY, "cache");
        hashOps.put(key, "user", cache.user);
        hashOps.put(key, "names", cache.names);
        hashOps.increment(key, "version", 1d);
    }

    public User getValue(String str) throws IllegalAccessException {
        String key = String.format("%s%s", USER_KEY, str);
        Map<String, Object> entries = hashOps.entries(key);
        Field[] fields = User.class.getDeclaredFields();
        User user = new User();
        for (Field field : fields) {
            field.setAccessible(true);
            field.set(user, entries.get(field.getName()));
        }
        return user;
    }

    public Cache getCache(String str) {
        String key = String.format("%s%s", USER_KEY, str);
        User user = (User) hashOps.get(key, "user");
        Set<String> names = (Set<String>) hashOps.get(key, "names");
        return new Cache(user, names);
    }

    public static class Cache {
        private User user;
        private Set<String> names;

        public Cache() {
        }

        public Cache(User user, Set<String> names) {
            this.user = user;
            this.names = names;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Set<String> getNames() {
            return names;
        }

        public void setNames(Set<String> names) {
            this.names = names;
        }

        @Override
        public String toString() {
            return "user:" + user + ", names: " + names;
        }
    }

    public static class User {

        private String name;
        private Integer gender;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getGender() {
            return gender;
        }

        public void setGender(Integer gender) {
            this.gender = gender;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "{name:" + name + ", gender:+" + gender + ", age:" + age + "}";
        }
    }

}
