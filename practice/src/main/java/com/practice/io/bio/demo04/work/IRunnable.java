package com.practice.io.bio.demo04.work;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 自定义任务
 *
 * @author chen.hong
 * @date 2022/2/26
 */
public class IRunnable implements Runnable {

    private Socket socket;

    public IRunnable(Socket socket) {
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
            System.out.println("有客户端下线了");
        }
    }
}
