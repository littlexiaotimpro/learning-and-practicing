package com.practice.io.bio.demo06;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * BIO 模式下的端口转发: 实现多客户端之间的消息收发机制（端口分发）
 * <p>
 * 1.接收客户端socket连接（独立线程处理）
 * 2.保存所有建立连接的 socket
 * 3.将接收的消息转发给建立连接的socket
 *
 * @date 2022/2/27
 */
public class ServerDemo06 {

    public final static List<Socket> onlineSocket = new ArrayList<>();

    public static class IThread extends Thread {
        private final Socket socket;

        public IThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // 接收客户端的字节输入
                InputStream inputStream = socket.getInputStream();
                // 字节输入转字符输入，便于读取
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(isr);
                String msg;
                while ((msg = br.readLine()) != null) {
                    System.out.println("服务端接收到数据: " + msg);
                    // 消息转发给其他客户端
                    for (Socket otherSocket : onlineSocket) {
                        OutputStream outputStream = otherSocket.getOutputStream();
                        PrintStream printStream = new PrintStream(outputStream);
                        printStream.println(msg);
                        printStream.flush();
                    }
                }
            } catch (IOException e) {
                System.out.println("客户端下线");
                onlineSocket.remove(socket);
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("服务端【8888】启动！");
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(8888);
            while (true) {
                Socket socket = serverSocket.accept();
                onlineSocket.add(socket);
                IThread iThread = new IThread(socket);
                iThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
