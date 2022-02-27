package com.practice.io.bio.demo06;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * BIO 模式下的端口转发: 实现多客户端之间的消息收发机制
 * <p>
 * 发送消息：
 * - 正常与服务端建立连接
 * - 使用打印流向服务端发送数据
 * <p>
 * 接收消息
 * - 接收客户端转发过来的消息（独立线程）
 *
 * @date 2022/2/27
 */
public class ClientDemo06_02 {

    public static class CThread extends Thread {
        private final Socket socket;

        public CThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // 接收服务端转发到客户端的字节输入
                InputStream inputStream = socket.getInputStream();
                // 字节输入转字符输入，便于读取
                InputStreamReader isr = new InputStreamReader(inputStream);
                BufferedReader br = new BufferedReader(isr);
                String msg;
                while ((msg = br.readLine()) != null) {
                    System.out.println("客户端【06_02】接收到消息: " + msg);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("客户端【06_02】启动！");
        Socket socket = new Socket("127.0.0.1", 8888);
        // 独立线程处理服务端转发过来的消息
        CThread cThread = new CThread(socket);
        cThread.start();
        Scanner input = new Scanner(System.in);
        while (true) {
            String msg = input.nextLine();
            if (msg == null || msg.isEmpty()) {
                break;
            }
            OutputStream outputStream = socket.getOutputStream();
            PrintStream printStream = new PrintStream(outputStream);
            printStream.println(msg);
            printStream.flush();
        }
    }

}
