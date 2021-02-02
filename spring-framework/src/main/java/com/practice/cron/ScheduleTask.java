package com.practice.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ScheduleTask {

    private final Logger log = LoggerFactory.getLogger(ScheduleTask.class);

    private final List<Integer> index = Arrays.asList(8 * 1000, 3 * 1000, 6 * 1000, 2 * 1000, 2 * 1000);

    private final AtomicInteger atomicInteger = new AtomicInteger(0);

    /**
     * fixedDelay
     * 在上一次调用的结束与下一次调用的开始之间以固定的毫秒数为周期执行带注释的方法
     * <p></p>
     * 也就是在上一个任务完成之后，等待固定的时间后执行下一个任务
     *
     * @throws Exception 异常
     */
    //@Scheduled(fixedDelay = 3 * 1000)
    public void fixedDelay() throws Exception {
        int i = atomicInteger.get();
        if (i < 5) {
            Integer sleepTime = index.get(i);
            log.info("fixedDelay ----> 第{}个任务开始执行,执行时间为{}ms", i, sleepTime);
            Thread.sleep(sleepTime);
            atomicInteger.getAndIncrement();
        }
    }

    /**
     * cron：秒，分，时，日，月，星期
     * 在固定的周期内执行任务，若到达下一个周期时上一个任务未完成，则自动顺延至下一周期
     * <p></p>
     * 如示例：
     * 第一个任务 0s 开始，执行时间是 8s，在第 5s 的时候第二个任务无法执行，自动顺延
     * 第二个任务 10s 开始，执行时间是 3s
     * 第三个任务 15s 开始，执行时间 6s，在第 20s 的时候第4个任务无法执行，自动顺延
     * ...
     *
     * @throws Exception 异常
     */
    //@Scheduled(cron = "0/5 * * * * ?")
    public void cron() throws Exception {
        int i = atomicInteger.get();
        if (i < 5) {
            Integer sleepTime = index.get(i);
            log.info("cron ----> 第{}个任务开始执行,执行时间为{}ms", i, sleepTime);
            Thread.sleep(sleepTime);
            atomicInteger.getAndIncrement();
        }
    }

    /**
     * fixedRate
     * 两次调用之间以固定的时间段（以毫秒为单位）执行带注释的方法。
     * <p></p>
     * 预计每个任务的完成时间是固定的，但存在任务超时的情况
     * 对于下一任务的开始时间：
     * 若已经超过了预计的开始时间，在直接在上一个任务的基础上直接进行
     * 若距离预计时间还有剩余，则等待剩余时间后任务开始
     * <p></p>
     * 如示例：固定间隔 5s
     * 任务1，预计在第 0s 开始，实际耗时 8s
     * 任务2，预计在第 5s 开始，但此时已经来到了第 8s，因此为了赶进度，直接接上任务1，耗时 3s
     * 任务3，预计在第 10s 开始，此时已经来到了第 11s(8+3)，同样，直接接上任务2，耗时 6s
     * 任务4，预计在第 15s 开始，此时已经来到了第 17s(11+6)，直接接上任务3，耗时 2s
     * 任务5，预计在第 20s 开始，此时已经来到了第 19s(17+2)，离预计时间还有 1s，此时等待 1s 后任务开始
     * ...
     *
     * @throws Exception 异常
     */
    @Scheduled(fixedRate = 5 * 1000)
    public void fixedRate() throws Exception {
        int i = atomicInteger.get();
        if (i < 5) {
            Integer sleepTime = index.get(i);
            log.info("fixedRate ----> 第{}个任务开始执行,执行时间为{}ms", i, sleepTime);
            Thread.sleep(sleepTime);
            atomicInteger.getAndIncrement();
        }
    }

}
