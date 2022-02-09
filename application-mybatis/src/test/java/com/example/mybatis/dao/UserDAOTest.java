package com.example.mybatis.dao;

import com.example.mybatis.entity.User;
import com.example.mybatis.utils.MapperUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.result.DefaultResultContext;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;

import static org.junit.Assert.*;

@Slf4j
public class UserDAOTest {

    @Test
    public void checkLogin() throws Exception {
        User test = MapperUtils.callMapper(UserDAO.class, mapper -> {
            User user = User.builder()
                    .account("admin")
                    .password("111111")
                    .build();
            return mapper.checkLogin(user);
        });
        assertNotNull("", test);
    }

    @Test
    public void updateAdmin() throws Exception {
        Integer res = MapperUtils.callMapper(UserDAO.class, mapper -> {
            User user = User.builder()
                    .id(4)
                    .status(User.Status.INVALID)
                    .account("mybatis_test_4")
                    .password("aaa@test.com")
                    .build();
            return mapper.updateAdmin(user);
        });
        assertEquals("", 1, res.intValue());
    }

    @Test
    public void saveUser() throws Exception {
        Integer res = MapperUtils.callMapper(UserDAO.class, mapper -> {
            User user = User.builder()
                    .id(6)
                    .status(User.Status.VALID)
                    .account("mybatis_test")
                    .password("aaa@test.com")
                    .build();
            return mapper.saveUser(user);
        });
        assertEquals("", 1, res.intValue());
    }

    @Test
    public void deleteUserByKey() throws Exception {
        Integer res = MapperUtils.callMapper(UserDAO.class, mapper -> {
            return mapper.deleteUserByKey(5);
        });
        assertEquals("", 1, res.intValue());
    }

    @Test
    public void findUserByKey() throws Exception {
        User user = MapperUtils.callMapper(UserDAO.class, mapper -> {
            return mapper.findUserByKey(1);
        });
        assertEquals("", User.Status.VALID, user.getStatus());
    }

    @Test
    public void findAllUser() throws Exception {
        MapperUtils.callMapper(UserDAO.class, mapper -> {
            mapper.findAllUser(resultContext -> {
                DefaultResultContext<User> context = (DefaultResultContext<User>) resultContext;
                User user = context.getResultObject();
                log.info("{}", user);
            });
            return null;
        });
    }

    //-------------------二级缓存测试---------------------

    @Test
    public void findUserByKey_cache_01() throws Exception {
        SqlSessionFactory sessionFactory = MapperUtils.build();
        try (SqlSession sqlSession = sessionFactory.openSession();) {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            userDAO.findAllUser(resultContext -> {
                DefaultResultContext<User> context = (DefaultResultContext<User>) resultContext;
                User user = context.getResultObject();
                log.info("{}", user);
            });
        }
        try (SqlSession sqlSession = sessionFactory.openSession();) {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            userDAO.findAllUser(resultContext -> {
                DefaultResultContext<User> context = (DefaultResultContext<User>) resultContext;
                User user = context.getResultObject();
                log.info("{}", user);
            });
        }
    }

    @Test
    public void findUserByKey_cache_02() throws Exception {
        SqlSessionFactory sessionFactory = MapperUtils.build();
        try (SqlSession sqlSession = sessionFactory.openSession();) {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            User user = userDAO.findUserByKey(1);
            log.info("{}", user);
        }
        // 重复查询之间执行增删改操作，清除缓存
        try (SqlSession sqlSession = sessionFactory.openSession();) {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            User user = User.builder()
                    .id(7)
                    .status(User.Status.VALID)
                    .account("mybatis_test")
                    .password("aaa@test.com")
                    .build();
            Integer res = userDAO.saveUser(user);
            log.info("{}", res);
        }
        try (SqlSession sqlSession = sessionFactory.openSession();) {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            User user = userDAO.findUserByKey(1);
            log.info("{}", user);
        }
    }

    @Test
    public void findUserByKey_cache_03() throws Exception {
        SqlSessionFactory sessionFactory = MapperUtils.build();
        // 配置flushCache属性值为true
        try (SqlSession sqlSession = sessionFactory.openSession();) {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            User user = userDAO.findUserByKey_flushCache(1);
            log.info("{}", user);
        }
        try (SqlSession sqlSession = sessionFactory.openSession();) {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            User user = userDAO.findUserByKey_flushCache(1);
            log.info("{}", user);
        }
    }

    @Test
    public void findUserByKey_cache_04() throws Exception {
        SqlSessionFactory sessionFactory = MapperUtils.build();
        // 配置useCache属性值为false
        try (SqlSession sqlSession = sessionFactory.openSession();) {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            User user = userDAO.findUserByKey_useCache(1);
            log.info("{}", user);
        }
        try (SqlSession sqlSession = sessionFactory.openSession();) {
            UserDAO userDAO = sqlSession.getMapper(UserDAO.class);
            User user = userDAO.findUserByKey_useCache(1);
            log.info("{}", user);
        }
    }
}