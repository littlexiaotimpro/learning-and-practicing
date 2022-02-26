package com.practice.io.bio.demo05;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.UUID;

/**
 * BIO 模式下的文件上传: 服务端接收文件
 *
 * @author chen.hong
 * @date 2022/2/26
 */
public class ServerDemo05 {

    protected static final String FILE_DIR = "D:\\Workspace\\Jetbrains\\idea\\learning-and-practicing\\practice\\src\\main\\dir";


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
                // 将字节输入流转换为数据输入流
                DataInputStream dis = new DataInputStream(inputStream);
                // 读取文件后缀
                String suffix = dis.readUTF();
                System.out.println("接收的文件后缀：" + suffix);
                // 定义字节输出流，将文件写入服务器
                try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_DIR + "\\"
                        + UUID.randomUUID().toString().replaceAll("-", "")
                        + suffix);
                     BufferedOutputStream bos = new BufferedOutputStream(fileOutputStream);) {
                    byte[] b = new byte[1024];
                    int len;
                    // 如果客户端不通知服务端数据已经发送完成，服务端会继续等待，但此时客户端断开socket管道连接，服务端报错，连接重置
                    while ((len = dis.read(b)) > 0) {
                        bos.write(b, 0, len);
                    }
                }finally {
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                IThread iThread = new IThread(socket);
                iThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
