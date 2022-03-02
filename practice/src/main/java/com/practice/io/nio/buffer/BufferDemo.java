package com.practice.io.nio.buffer;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

/**
 * 缓存区 Buffer 应用测试
 *
 * @date 2022/3/1
 */
public class BufferDemo {

    public static void main(String[] args) {
        // 字节缓冲区
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        System.out.println("----------------初始化缓存区----------------");
        show(byteBuffer);

        // 实际操作
        String data = "data";
        // 往缓冲区写入数据
        System.out.println("----------------往缓冲区写入数据----------------");
        byteBuffer.put(data.getBytes(StandardCharsets.UTF_8));
        show(byteBuffer);
        // 切换可读模式
        System.out.println("----------------切换可读模式----------------");
        byteBuffer.flip();
        show(byteBuffer);
        // 读取缓冲区数据
        System.out.println("----------------读取缓冲区数据----------------");
        byte b = byteBuffer.get();
        System.out.println("get: " + ((char) b));
        show(byteBuffer);
        // 清除缓冲区
        System.out.println("----------------清除缓冲区----------------");
        // 还原当前位置（position）为：0
        byteBuffer.clear();
        show(byteBuffer);
    }

    /**
     * 参数属性
     */
    private static void show(Buffer buffer) {
        // 返回当前位置
        System.out.println("position: " + buffer.position());
        // 限制位置
        System.out.println("limit: " + buffer.limit());
        // 返回容量
        System.out.println("capacity: " + buffer.capacity());
        // 返回标记
        System.out.println("mark: " + buffer.mark());
    }

}
