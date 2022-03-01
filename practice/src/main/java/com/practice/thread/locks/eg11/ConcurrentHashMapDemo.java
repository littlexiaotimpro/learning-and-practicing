package com.practice.thread.locks.eg11;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;

/**
 * 对比 HashMap 和 ConcurrentHashMap
 *
 * @date 2022/2/28
 */
public class ConcurrentHashMapDemo {

    /**
     * 首先HashMap是线程不安全的，其主要体现：
     *
     * <p> 在 jdk1.7 中，在多线程环境下，扩容时会造成环形链或数据丢失。
     * <p> 在 jdk1.8 中，在多线程环境下，会发生数据覆盖的情况。
     *
     * <p> 二者区别：
     * - jdk7的rehash操作采用的是取模的运算，效率远不如位运算，jdk8在resize()的过程中，取消了rehash的过程
     * - 通过如下4组指针解决了环状链表出现，并将长链表分散到扩容后的数组中（链表低位指针索引直接迁移，链表高位指针索引+oldCap）
     * Node<K,V> loHead = null, loTail = null;
     * Node<K,V> hiHead = null, hiTail = null;
     *
     * <p> jdk8 的 hashMap 属性：
     * - TREEIFY_THRESHOLD: 树化阈值，在加载因子为0.75时计算出来的空间与时间的折中结果（泊松分布，当链表长度为8的时候，发生hash碰撞的概率已经逐渐趋近于0）
     */
    private static void hashMap() throws BrokenBarrierException, InterruptedException {
        Map<String, String> map = new HashMap<>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                    TimeUnit.MILLISECONDS.sleep(((int) (Math.random() * 10)) * 1000L);
                    for (int i1 = 0; i1 < 10; i1++) {
                        String key = "k" + i1;
                        String value = "v" + i1 + "-" + Thread.currentThread().getName();
                        map.put(key, value);
                    }
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }, "t" + i).start();
        }
        TimeUnit.SECONDS.sleep(6);
        System.out.println(map);
    }

    /**
     * ConcurrentHashMap
     *
     * <p> jdk7
     * 使用锁分段计数解决线程安全问题，首先将数据分成一段一段的存储，然后给每一段数据配一把锁，当一个线程占用锁访问其中一个段数据的时候，
     * 其他段的数据也能被其他线程访问。 另外，ConcurrentHashMap可以做到读取数据不加锁，
     * 并且其内部的结构可以让其在进行写操作的时候能够将锁的粒度保持地尽量地小，不用对整个ConcurrentHashMap加锁
     * <p>
     * ConcurrentHashMap是由Segment数组结构和HashEntry数组结构组成。
     * Segment是一种可重入锁ReentrantLock，在ConcurrentHashMap里扮演锁的角色，HashEntry则用于存储键值对数据。
     * 一个ConcurrentHashMap里包含一个Segment数组，Segment的结构和HashMap类似，是一种数组和链表结构，
     * 一个Segment里包含一个HashEntry数组，每个HashEntry是一个链表结构的元素， 每个Segment守护着一个HashEntry数组里的元素，
     * 当对HashEntry数组的数据进行修改时，必须首先获得它对应的Segment锁。
     *
     * <p> jd8
     * 读和写没有使用读写锁进行互斥，也不能保证强一致性，即线程2并不一定就能够读取到线程1修改后的值
     */
    private static void concurrentHashMap() throws InterruptedException {
        Map<String, String> map = new ConcurrentHashMap<>();
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(((int) (Math.random() * 10)) * 1000L);
                    for (int i1 = 0; i1 < 10; i1++) {
                        String key = "k" + i1;
                        String value = "v" + i1 + "-" + Thread.currentThread().getName();
                        map.put(key, value);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            }, "t" + i).start();
        }
        countDownLatch.await();
        System.out.println(map);
    }

    public static void main(String[] args) {
        new Thread(() -> {
            try {
                hashMap();
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                concurrentHashMap();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

}
