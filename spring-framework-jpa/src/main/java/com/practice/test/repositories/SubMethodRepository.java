package com.practice.test.repositories;

import com.practice.test.entity.Log;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubMethodRepository extends MethodRepository<Log, String> {

    List<Log> findByOperator(String operator);
}
