package com.practice.io.bio.demo04;

import com.practice.io.bio.demo04.work.IRunnable;
import com.practice.io.bio.demo04.work.SocketServerHandlerPool;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO 的应用服务端: 实现伪异步通信
 * <p>
 * 引入线程池和任务等待队列
 *
 * @date 2022/2/26
 */
public class ServerDemo04 {

    public static void main(String[] args) {
        try {
            System.out.println("启动服务端！");
            // 定义一个Server套接字对象注册服务端口
            ServerSocket serverSocket = new ServerSocket(8888);
            SocketServerHandlerPool pool = new SocketServerHandlerPool(2, 3);
            while (true) {
                // 监听客户端的Socket链接请求，服务启动后会在此处阻塞，带响应到连接请求后继续
                Socket socket = serverSocket.accept();
                pool.execute(new IRunnable(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
