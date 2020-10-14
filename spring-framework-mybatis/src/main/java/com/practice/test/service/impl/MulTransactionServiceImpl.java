package com.practice.test.service.impl;

import com.practice.test.service.LogService;
import com.practice.test.service.MulTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MulTransactionServiceImpl implements MulTransactionService {

    @Autowired
    private LogService logService;

    /**
     * 验证各种传播行为属性的作用
     * 1.事务一、二使用默认传播属性值（REQUIRED）
     *  1.1.因为存在当前事务，事务都运行在该事务中，异常后所有事务都回滚
     *  1.2.不存在当前事务，子事务开启新事务，异常后，事务已经完成，不会回滚
     */
    @Override
//    @Transactional
    public void checkPropagation() {
        // 嵌套事务一
        logService.transactionOne("720", "720-test-supports");
        // 嵌套事务二
        logService.transactionTwo("721", "721-test-requires-new");
        // 异常
//        int i = 1 / 0;
    }
}
