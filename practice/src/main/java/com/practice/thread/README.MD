## 多线程

了解线程基本的使用，并学习相关实现原理，文档结构如下：

```文件夹 PATH 列表
多线程学习模块的文档目录结构
thread
├─aqs               AbstractQueuedSynchronizer
├─base              线程基本实现
│  ├─callable       Callable
│  ├─common         为基本线程模块提供公有的线程操作对象
│  ├─sync           线程间通信：管道Piped|volatile|synchronized
│  ├─threadgroup    线程组
│  └─threadmethod   线程基本方法
├─bounce            多线程示例
├─executor          线程池
├─locks             多线程示例
│  ├─eg1            示例1：卖票，synchronized实现            
│  ├─eg2            示例2：卖票，ReentrantLock实现
│  ├─eg3            示例3：线程虚假唤醒
│  ├─eg4            示例4：死锁
│  ├─eg5            示例5：定制化线程执行顺序
│  ├─eg6            同步阻塞队列实现
│  ├─eg7            LockSupport实现线程同步
│  └─eg8            Semaphore实现线程同步
├─threadlocal       线程本地变量
└─unsynch           银行转账示例
```
### 虚假唤醒
在上诉对应的示例3中描述在线程同步时出现的异常现象，指的是当一个正在等待条件变量的线程由于条件变量被触发而唤醒时，却发现它等待的条件（共享数据）没有满足(也就是没有共享数据)。

```java
class Source {

    private int number = 0;

    public synchronized void add() throws InterruptedException {
        /*
         * 如下：
         * 需要实现的场景是number的值随线程交替执行[+1|-1]操作，及number的值只有[0,1]两种
         * 但线程执行过程中因为意外的条件满足，出现了预料之外的结果，如：-1
         * 原因是在多线程下，某一个线程调用notifyAll()唤醒了多个线程，因为if条件已经执行，线程在哪里等待就在哪里唤醒
         * 因此跳出if代码块，直接继续执行后续逻辑操作，导致出现不符合预期的结果
         * 
         * 解决方案，if -> while：被唤醒的线程，循环检查自己是否真的满足了唤醒条件，若不满足，继续等待
         */
        if (number != 0) {
            // 在哪里等待，在哪里唤醒
            this.wait();
        }
        number++;
        final Thread thread = Thread.currentThread();
        System.out.printf("synchronized, 线程: %s, number: %d\n", thread.getName(), number);
        this.notifyAll();
    }

    public synchronized void subtract() throws InterruptedException {
        if (number != 1) {
            this.wait();
        }
        number--;
        final Thread thread = Thread.currentThread();
        System.out.printf("synchronized, 线程: %s, number: %d\n", thread.getName(), number);
        this.notifyAll();
    }
}
```

### 锁

Java多线程的锁都是基于对象的，Java中的每一个对象都可以作为一个锁。

还有一点需要注意的是，我们常听到的类锁其实也是对象锁。

Java类只有一个Class对象（可以有多个实例对象，多个实例共享这个Class对象），而Class对象也是特殊的Java对象。所以我们常说的类锁，其实就是Class对象的锁。

### synchronized

本质上使用的是对象锁

```java
class S {
    // 关键字在实例方法上，锁为当前实例
    public synchronized void instanceLock() {
        // code
    }

    // 关键字在静态方法上，锁为当前Class对象
    public static synchronized void classLock() {
        // code
    }

    // 关键字在代码块上，锁为括号里面的对象
    public void blockLock() {
        Object o = new Object();
        synchronized (o) {
            // code
        }
    }
}
```