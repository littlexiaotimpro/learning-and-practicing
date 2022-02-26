package com.practice.io.bio.demo01;

import java.io.*;
import java.net.Socket;

/**
 * BIO 的应用客户端
 *
 * @date 2022/2/26
 */
public class ClientDemo01 {

    public static void main(String[] args) {
        try {
            System.out.println("启动客户端！");
            // 创建请求服务端的连接
            Socket socket = new Socket("127.0.0.1", 8888);
            // 获取字节输出流
            OutputStream outputStream = socket.getOutputStream();
            // 转换字节输出流为打印流
            PrintStream printStream = new PrintStream(outputStream);
            // 打印消息，服务端按行读取信息，则客户端必须按行发送信息，否则服务端将进入等待消息的阻塞状态
            printStream.println("Hello World!");
            // 刷新服务
            printStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
