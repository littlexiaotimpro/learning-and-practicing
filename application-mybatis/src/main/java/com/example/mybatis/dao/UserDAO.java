package com.example.mybatis.dao;

import com.example.mybatis.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ResultHandler;

public interface UserDAO extends MyBatisBaseDao<User, Integer> {

    User checkLogin(@Param("admin") User admin);

    Integer updateAdmin(@Param("admin") User admin);

    Integer saveUser(@Param("admin") User user);

    Integer deleteUserByKey(@Param("userId") Integer userId);

    User findUserByKey(@Param("userId") Integer userId);
    User findUserByKey_flushCache(@Param("userId") Integer userId);
    User findUserByKey_useCache(@Param("userId") Integer userId);

    void findAllUser(ResultHandler<User> resultHandler);
}
