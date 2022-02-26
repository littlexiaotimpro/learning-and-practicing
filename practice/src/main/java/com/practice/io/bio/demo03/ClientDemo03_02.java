package com.practice.io.bio.demo03;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * BIO 的应用客户端: 一个服务端同时多个客户端的通信需求，引入多线程
 *
 * @date 2022/2/26
 */
public class ClientDemo03_02 {

    public static void main(String[] args) {
        try {
            System.out.println("启动客户端【02】");
            // 创建请求服务端的连接
            Socket socket = new Socket("127.0.0.1", 8888);
            // 获取字节输出流
            OutputStream outputStream = socket.getOutputStream();
            // 转换字节输出流为打印流
            PrintStream printStream = new PrintStream(outputStream);
            Scanner scanner = new Scanner(System.in);
            while (true) {
                // 通过扫描器的形式不断的发送消息
                System.out.print("消息: ");
                String s = scanner.nextLine();
                if (s == null || s.isEmpty()) {
                    break;
                }
                // 打印消息，服务端按行读取信息，则客户端必须按行发送信息，否则服务端将进入等待消息的阻塞状态
                printStream.println(s);
                // 刷新服务
                printStream.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
