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
├─juc               并发工具类
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
### 线程池
```
public ThreadPoolExecutor(int corePoolSize,
  int maximumPoolSize,
  long keepAliveTime,
  TimeUnit unit,
  BlockingQueue<Runnable> workQueue,
  ThreadFactory threadFactory,
  RejectedExecutionHandler handler) {
  // code    
}
```
构造器属性介绍
- corePoolSize  
  线程池中的核心线程数  
  提交任务优先创建核心线程处理（即使存在其他空闲线程），等到工作的线程数大于核心线程数时就新任务不会再创建核心线程了
  如果调用了线程池的prestartAllCoreThreads方法，线程池会提前把核心线程都创造好，并启动
- maximumPoolSize  
  线程池允许创建的最大线程数。  
  如果队列满了，并且已创建的线程数小于最大线程数，则线程池会再创建新的线程执行任务。  
  如果我们使用了无界队列，那么所有的任务会加入队列，这个参数就没有什么效果了
- keepAliveTime  
  线程池的工作线程空闲后，保持存活的时间。  
  任务处理完成之后，线程空闲的时间超过这个值，就会被回收掉。  
  如果任务很多，并且每个任务都能在有限时间内处理完成，则可以适当修改（增大）此值，以提高线程利用率。
- unit  
  线程存活的时间单位，类型是一个枚举java.util.concurrent.TimeUnit
- workQueue  
  工作队列，用于缓存待处理任务的阻塞队列
  - ArrayBlockingQueue  
    一个基于数组结构的有界阻塞队列，此队列按照先进先出原则对元素进行排序
  - LinkedBlockingQueue  
    一个基于链表结构的阻塞队列，此队列按照先进先出排序元素，吞吐量通常要高于ArrayBlockingQueue。  
    静态工厂方法Executors.newFixedThreadPool使用了这个队列。
  - SynchronousQueue  
    一个不存储元素的阻塞队列，每个插入操作必须等到另外一个线程调用移除操作，否则插入操作一直处理阻塞状态，吞吐量通常要高于LinkedBlockingQueue  
    静态工厂方法Executors.newCachedThreadPool使用这个队列
  - PriorityBlockingQueue  
    优先级队列，进入队列的元素按照优先级排序
- threadFactory  
  线程池中创建线程的工厂
- handler  
  饱和策略，当线程池无法处理新来的任务了，那么需要提供一种策略处理提交的新任务，如下：
  - AbortPolicy：直接抛出异常
  - CallerRunsPolicy：在当前调用者的线程中运行任务，即随丢来的任务，由他自己去处理
  - DiscardOldestPolicy：丢弃队列中最老的一个任务，即丢弃队列头部的一个任务，然后执行当前传入的任务
  - DiscardPolicy：不处理，直接丢弃掉，方法内部为空
  
扩展线程池
- beforeExecute  
  任务执行之前调用的方法，有2个参数，第1个参数是执行任务的线程，第2个参数是任务
- afterExecute  
  任务执行完成之后调用的方法，2个参数，第1个参数表示任务，第2个参数表示任务执行时的异常信息，如果无异常，第二个参数为null
- terminated  
  线程池最终关闭之后调用的方法。所有的工作线程都退出了，最终线程池会退出，退出时调用该方法
  
线程池参数配置

线程池中总线程大小对系统的性能有一定的影响，我们的目标是希望系统能够发挥最好的性能，过多或者过小的线程数量无法有效的使用机器的性能。

在Java Concurrency in Practice书中给出了估算线程池大小的公式：

```
Ncpu = CUP的数量
Ucpu = 目标CPU的使用率，0<=Ucpu<=1
W/C = 等待时间与计算时间的比例
为保存处理器达到期望的使用率，最有的线程池的大小等于：
Nthreads = Ncpu × Ucpu × (1+W/C)
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