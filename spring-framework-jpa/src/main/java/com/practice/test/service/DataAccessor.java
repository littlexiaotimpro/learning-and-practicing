package com.practice.test.service;

import com.practice.test.entity.Log;

public interface DataAccessor {

    void save(Log log);

    void delete(Log log);

}
