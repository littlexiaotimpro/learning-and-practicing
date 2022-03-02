package com.practice.io.nio.demo01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * NIO 模式下的通信
 *
 * @date 2022/3/2
 */
public class ClientDemo01 {
    public static void main(String[] args) throws IOException {
        InetSocketAddress remote = new InetSocketAddress(8888);
        SocketChannel socketChannel = SocketChannel.open(remote);
        socketChannel.connect(remote);

        ByteBuffer buffer = ByteBuffer.allocate(1024);
        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.print("msg: ");
            String line = input.nextLine();
            if (line == null || line.isEmpty()) {
                break;
            }
            String msg = System.currentTimeMillis() + ": " + line;
            byte[] bytes = msg.getBytes(StandardCharsets.UTF_8);
            buffer.put(bytes);
            // 切换可读模式
            buffer.flip();
            socketChannel.write(buffer);
            buffer.clear();

            int read = socketChannel.read(buffer);
            buffer.flip();
            byte[] bytes1 = new byte[read];
            buffer.get(bytes1);
            System.out.println("服务端返回的消息: " + new String(bytes1, StandardCharsets.UTF_8));
            buffer.clear();
        }
    }
}
