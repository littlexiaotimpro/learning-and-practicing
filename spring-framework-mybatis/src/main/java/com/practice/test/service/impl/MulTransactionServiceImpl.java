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
     * 1.1.因为存在当前事务，事务都运行在该事务中，异常后所有事务都回滚
     * 1.2.不存在当前事务，子事务开启新事务，异常后，事务已经完成，不会回滚
     */
    @Override
    @Transactional
    public void checkPropagation01() {
        // 嵌套事务一
        logService.transactionRequired("720", "720-test-required");
        // 嵌套事务二
        logService.transactionRequired("721", "721-test-required");
        // 异常
        int i = 1 / 0;
    }

    /**
     * 验证各种传播行为属性的作用
     * 1.因为存在当前事务，事务一（Required）和主事务中共享一个事务
     * 2.事务二开启新事务运行，在异常发生之前就已经提交，不受异常影响回滚
     */
    @Override
    @Transactional
    public void checkPropagation02() {
        // 嵌套事务一
        logService.transactionRequired("720", "720-test-required");
        // 嵌套事务二
        logService.transactionRequiresNew("721", "721-test-requires_new");
        // 异常
        int i = 1 / 0;
    }

    /**
     * 验证各种传播行为属性的作用
     * 1.因为存在当前事务，事务一（Required）和主事务中共享一个事务
     * 2.事务二开启新事务运行，内部抛出异常，事务进行回滚
     * 因为，对于事务二抛出的异常，在主事务中没有进行捕获，因此主事务检测到了该异常
     * 进行回滚，事务一和主事务共享一个事务。
     */
    @Override
    @Transactional
    public void checkPropagation03() {
        // 嵌套事务一
        logService.transactionRequired("720", "720-test-required");
        // 嵌套事务二
        logService.transactionRequiresNewThrow("721", "721-test-requires_new_throw");
    }

    /**
     * 必须依赖主事务，不存在则抛异常
     */
    @Override
    public void checkPropagation04() {
        logService.transactionMandatory("720", "720-test-mandatory");
        int a = 1 / 0;
    }

    /**
     * 当前方法不需要事务上下文的支持
     * 若存在当前事务，则运行在该事务中
     * 若不存在，则以非事务的方式运行该方法
     */
    @Override
    @Transactional
    public void checkPropagation05() {
        logService.transactionSupports("720", "720-test-supports");
        int a = 1 / 0;
    }

    /**
     * 当前方法以非事务的方式运行，若存在当前事务，则将该事务挂起
     */
    @Override
    @Transactional
    public void checkPropagation06() {
        logService.transactionNotSupported("720", "720-test-not_supported");
        int a = 1 / 0;
    }

    /**
     * 以非事务方式运行，存在事务则抛异常
     */
    @Override
    @Transactional
    public void checkPropagation07() {
        logService.transactionNever("720", "720-test-never");
        int a = 1 / 0;
    }

    @Override
    @Transactional
    public void checkPropagationConcurrent() {
        // 嵌套事务一
        logService.transactionRequired("720", "720-test-required");
        // 嵌套事务二
        new Thread(() -> {
            logService.transactionRequired("722", "722-test-required");
            logService.transactionRequiresNewThrow("721", "721-test-requires_new_throw");
        }).start();
        int a = 1 / 0;
    }
}
