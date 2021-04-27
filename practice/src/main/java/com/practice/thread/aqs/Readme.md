### 线程中断的原理
----
Java 的线程阻塞和唤醒是通过 Unsafe 类的 park 和 unpark 方法做到的。
```java
public class Unsafe {
  public native void park(boolean isAbsolute, long time);
  public native void unpark(Thread t);
}
```
