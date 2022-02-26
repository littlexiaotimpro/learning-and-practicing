package com.practice.io.bio.demo01;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * BIO 的应用服务端
 *
 * @date 2022/2/26
 */
public class ServerDemo01 {
    public static void main(String[] args) {
        try {
            System.out.println("启动服务端！");
            // 定义一个Server套接字对象注册服务端口
            ServerSocket serverSocket = new ServerSocket(8888);
            // 监听客户端的Socket链接请求（没有客户端连接时，在此处阻塞）
            Socket socket = serverSocket.accept();
            // 从socket管道中得到一个字节输入流
            InputStream inputStream = socket.getInputStream();
            // 包装字节输入流为字符输入流
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
            String msg;
            // 客户端未传递数据时，会进入阻塞
            // 若客户端发送的消息不存在换行，发送完消息就宕机了，服务端此处默认一定要读取到行数据才行，但Socket管道已断，导致服务端报错，连接重置
            if ((msg = br.readLine()) != null) {
                System.out.println("服务端接收数据: " + msg);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
