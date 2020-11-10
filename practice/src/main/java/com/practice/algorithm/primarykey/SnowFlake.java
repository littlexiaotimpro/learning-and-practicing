package com.practice.algorithm.primarykey;

import java.util.Calendar;

/**
 * @author XiaoSi
 * @className SnowFlake
 * @description 分布式主键生成策略【雪花算法】
 * @date 2020/11/10
 * <p>
 * SnowFlake算法用来生成64位的ID，刚好可以用long整型存储，
 * 能够用于分布式系统中生产唯一的ID， 并且生成的ID有大致的顺序。
 * 生成的64位ID可以分成5个部分：
 * 符号位0 - 41位时间戳 - 5位数据中心标识 - 5位机器标识 - 12位序列号
 * <p>
 * 依据序列号位数，一毫秒单机可以最多可以生成 2^12 = 4096 个序列号
 */
public class SnowFlake {

    /**
     * 起始的时间戳: 2020-01-01
     */
    private final static long START_TIMESTAMP = 1580486400000L;

    /**
     * 每一部分占用的位数
     */
    private final static long SEQUENCE_BIT = 12; //序列号占用的位数
    private final static long MACHINE_BIT = 5;   //机器标识占用的位数
    private final static long DATA_CENTER_BIT = 5;//数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private final static long MAX_DATA_CENTER_NUM = ~(-1L << DATA_CENTER_BIT);
    private final static long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private final static long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private final static long MACHINE_LEFT = SEQUENCE_BIT;
    private final static long DATA_CENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private final static long TIMESTAMP_LEFT = DATA_CENTER_LEFT + DATA_CENTER_BIT;

    private long dataCenterId;  //数据中心
    private long machineId;     //机器标识
    private long sequence = 0L; //序列号
    private long lastStamp = -1L;//上一次时间戳

    public SnowFlake(long dataCenterId, long machineId) {
        if (dataCenterId > MAX_DATA_CENTER_NUM || dataCenterId < 0) {
            throw new IllegalArgumentException("dataCenterId can't be greater than MAX_DATA_CENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        this.dataCenterId = dataCenterId;
        this.machineId = machineId;
    }

    /**
     * 生成序列号
     */
    public synchronized long generateId() {
        // 获取系统当前时间戳
        long currStamp = getTimestamp();
        if (currStamp < lastStamp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }

        if (currStamp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStamp = getNextTimestamp();
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStamp = currStamp;
        long l = (currStamp - START_TIMESTAMP) << TIMESTAMP_LEFT;
        long l1 = dataCenterId << DATA_CENTER_LEFT;
        long l2 = machineId << MACHINE_LEFT;
        long l3 = sequence;
        System.out.println(l + "-" + l1 + "-" + l2 + "-" + l3);

        //时间戳部分 | 数据中心部分 | 机器标识部分 | 序列号部分
        return l | l1 | l2 | l3;
    }

    /**
     * 获取系统时间戳
     */
    private long getNextTimestamp() {
        long mill = getTimestamp();
        while (mill <= lastStamp) {
            mill = getTimestamp();
        }
        return mill;
    }

    private long getTimestamp() {
        return System.currentTimeMillis();
    }

    /**
     * 获取指定日期时间戳
     */
    private void getAssignDateMills(){
        Calendar build = new Calendar.Builder()
                .setDate(2020, 1, 1)
                .build();
        System.out.println(build.getTimeInMillis());
    }
}
