package com.practice.test.repositories;

import com.practice.test.entity.Log;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubMethodRepository extends MethodRepository<Log, String> {

    List<Log> findByOperator(String operator);

    List<Log> findByOperation(String operation, Pageable pageable);

    /**
     * {@link Query}
     * 使用 JPA 支持的语法
     * 需要注意，使用原生SQL时，需要设置 nativeQuery 属性值为 true
     */
    @Query(value = "select * from tb_log t where t.content like %?1%", nativeQuery = true)
    List<Log> queryLogs(String content);

    @Query(value = "select * from tb_log where operation = ?1 limit 3", nativeQuery = true)
    List<Log> selectByLimitOperation(String operation);
}
