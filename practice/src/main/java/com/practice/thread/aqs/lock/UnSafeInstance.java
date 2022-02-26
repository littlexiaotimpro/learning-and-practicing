package com.practice.thread.aqs.lock;

import lombok.extern.slf4j.Slf4j;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 获取 UnSafe 实例
 *
 * <p>Unsafe类为一单例实现，提供静态方法getUnsafe获取Unsafe实例，
 * 当且仅当调用getUnsafe方法的类为引导类加载器所加载时才合法，
 * 否则抛出SecurityException异常
 *
 * <p>那如若想使用这个类，该如何获取其实例？有如下两个可行方案:
 *
 * <p>其一，从getUnsafe方法的使用限制条件出发，通过Java命令行命令-Xbootclasspath/a把调用Unsafe相关方法的类A所在jar包路径追加到默认的bootstrap路径中，使得A被引导类加载器加载，从而通过Unsafe.getUnsafe方法安全的获取Unsafe实例。
 * <code>
 *     java -Xbootclasspath/a: ${path}   // 其中path为调用Unsafe相关方法的类所在jar包路径
 * </code>
 *
 * <p>其二，通过反射获取单例对象theUnsafe。
 * <code>
 * private static Unsafe reflectGetUnsafe() {
 *     try {
 *       Field field = Unsafe.class.getDeclaredField("theUnsafe");
 *       field.setAccessible(true);
 *       return (Unsafe) field.get(null);
 *     } catch (Exception e) {
 *       log.error(e.getMessage(), e);
 *       return null;
 *     }
 * }
 * </code>
 *
 * @date 2022/2/25
 */
@Slf4j
public class UnSafeInstance {

    public static Unsafe reflect() {
        try {
            Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            return (Unsafe) theUnsafe.get(null);
        } catch (IllegalAccessException | NoSuchFieldException e) {
            log.error(e.getMessage(), e);
            throw new RuntimeException("获取UnSafe实例失败！");
        }
    }

}
