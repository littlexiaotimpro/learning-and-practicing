package com.practice.io.nio.demo01;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

/**
 * NIO 模式下的通信
 *
 * @date 2022/3/2
 */
public class ServerDemo01 {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress("0.0.0.0", 8888), 50);
        serverSocketChannel.configureBlocking(false);
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (!key.isValid()) {
                    continue;
                }

                if (key.isAcceptable()) {
                    ServerSocketChannel serverChannel = (ServerSocketChannel) key.channel();
                    SocketChannel clientChannel = serverChannel.accept();
                    clientChannel.configureBlocking(false);
                    clientChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    ByteBuffer buffer = ByteBuffer.wrap(new byte[1024]);
                    SocketChannel clientChannel = (SocketChannel) key.channel();
                    int read = clientChannel.read(buffer);

                    if (read == -1) {
                        System.out.println("客户端断开连接");
                        key.cancel();
                        clientChannel.close();
                    } else {
                        buffer.flip();
                        clientChannel.write(buffer);

                        byte[] bytes1 = new byte[clientChannel.read(buffer)];
                        System.out.println("客户端发送的消息: " + new String(bytes1, StandardCharsets.UTF_8));
                    }
                }
            }
            iterator.remove();
        }
    }
}
