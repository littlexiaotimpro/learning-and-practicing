package com.practice.io.bio.demo05;

import java.io.*;
import java.net.Socket;

/**
 * BIO 模式下的文件上传
 *
 * @date 2022/2/26
 */
public class ClientDemo05 {

    public static void main(String[] args) {
        // 把文件数据发送给服务端
        try (FileInputStream fis = new FileInputStream("D:\\Workspace\\Jetbrains\\idea\\learning-and-practicing\\practice\\src\\main\\dir\\file_1.txt")) {
            Socket socket = new Socket("127.0.0.1", 8888);
            // 获取套接字的字节输出流
            OutputStream outputStream = socket.getOutputStream();
            // 将字节输出流转换为数据输出流
            DataOutputStream dos = new DataOutputStream(outputStream);
            // 先发送上传文件的后缀给服务端（修改后缀类型，实现不同类型的文件上传）
            dos.writeUTF(".txt");
            byte[] b = new byte[1024];
            int len;
            while ((len = fis.read(b)) > 0) {
                dos.write(b, 0, len);
            }
            dos.flush();
            // 通知服务端，数据发送完成
            socket.shutdownOutput();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
