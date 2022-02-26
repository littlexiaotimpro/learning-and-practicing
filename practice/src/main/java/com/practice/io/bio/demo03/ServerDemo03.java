package com.practice.io.bio.demo03;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO 的应用服务端: 一个服务端同时多个客户端的通信需求，引入多线程
 * <p>
 * 方案：服务端每接收到一个客户端的请求对象之后都开启一个新线程来独立完成数据交互
 * <p>
 * 缺点：
 * - 一个客户端连接请求就需要创建一个线程，每个线程都会占用栈空间和CPU资源，线程的竞争调度影响性能
 * - 并不是每个socket都进行IO操作（连接了但不作为），无意义的线程处理
 * - 客户端并发访问增加时，服务端将呈现 1:1 的线程开销，
 * 访问量越大，系统将发生线程栈溢出，导致后续线程创建失败，最终导致进程宕机或僵死，从而导致服务不可用
 *
 * @date 2022/2/26
 */
public class ServerDemo03 {

    /**
     * 处理客户端请求
     */
    private static class IThread extends Thread {
        private final Socket socket;

        public IThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // 从socket管道中得到一个字节输入流
                InputStream inputStream = socket.getInputStream();
                // 将字节输入流转换为字符输入流
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                // 缓存字符输入流
                BufferedReader br = new BufferedReader(inputStreamReader);
                String msg;
                // 在此处阻塞，并等待客户端发送消息
                // 按行读取数据，若客户端发送的消息不存在换行，则无法读取信息，连接重置
                while ((msg = br.readLine()) != null) {
                    System.out.println("服务端接收数据: " + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            System.out.println("启动服务端！");
            // 定义一个Server套接字对象注册服务端口
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                // 监听客户端的Socket链接请求，服务启动后会在此处阻塞，带响应到连接请求后继续
                Socket socket = serverSocket.accept();
                IThread iThread = new IThread(socket);
                iThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
