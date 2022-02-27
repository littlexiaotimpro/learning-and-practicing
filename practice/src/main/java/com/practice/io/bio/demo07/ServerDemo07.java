package com.practice.io.bio.demo07;

import com.practice.io.bio.demo07.constant.Constant;
import com.practice.io.bio.demo07.constant.Flag;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * BIO 模式下的端口转发: 实现多客户端之间的消息收发机制
 * <p>
 * 1.接收客户端socket连接（独立线程处理），每一个客户端都带有唯一标识
 * 2.保存所有建立连接的 socket
 * 3.将接收的消息转发给建立连接的socket
 *
 * @date 2022/2/27
 */
public class ServerDemo07 {

    public final static Map<Socket, String> onlineSocket = new HashMap<>();

    public static class IThread extends Thread {
        private final Socket socket;

        public IThread(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            DataInputStream dis = null;
            try {
                // 接收客户端的字节输入
                InputStream inputStream = socket.getInputStream();
                // 字节输入转字符输入，便于读取
                dis = new DataInputStream(inputStream);
                while (true) {
                    // 获取操作标识:{0:登录, 1:群发, 2:@, 3:私聊}
                    int flag = dis.readInt();
                    if (flag == 0) {
                        // 记录当前登录的客户端信息
                        String name = dis.readUTF();
                        System.out.println(name + "------->" + socket.getRemoteSocketAddress());
                        onlineSocket.put(socket, name);
                    }
                    Flag f = Flag.convert(flag);
                    writeMsg(f, dis);
                }
            } catch (IOException e) {
                System.out.println("--客户端下线--");
                onlineSocket.remove(socket);
                try {
                    // 发送当前在线客户端变更消息给其他客户端
                    writeMsg(Flag.LOGIN, dis);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void writeMsg(Flag flag, DataInputStream dis) throws IOException {
            switch (flag) {
                case LOGIN:
                    // 获取所有客户端信息
                    Collection<String> socketClients = onlineSocket.values();
                    String msg = String.join(Constant.SPLIT, socketClients);
                    sendMsgToOthers(flag, msg);
                    break;
                case TO_ALL:
                case TO_SOMEONE:
                    msg = dis.readUTF();
                    String client = onlineSocket.get(socket);
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE");
                    String format = dateFormat.format(System.currentTimeMillis());
                    // 群发|@
                    String stringBuilder = client + "  " + format + ":\r\n" + "  " + msg + "\r\n";
                    sendMsgToOthers(flag, stringBuilder);
                    break;
                case TO_DEST_ONE:
                    msg = dis.readUTF();
                    client = onlineSocket.get(socket);
                    dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss EEE");
                    format = dateFormat.format(System.currentTimeMillis());
                    // 群发|@
                    stringBuilder = client + "  " + format + ":\r\n" + "  " + msg + "\r\n";
                    String destClient = dis.readUTF();
                    sendMsgToOne(destClient, stringBuilder);
                    break;
            }
        }

        /**
         * 发给指定的客户端
         *
         * @param destClient 指定客户端名称
         * @param msg        消息
         * @throws IOException 异常
         */
        private void sendMsgToOne(String destClient, String msg) throws IOException {
            for (Map.Entry<Socket, String> socketEntry : onlineSocket.entrySet()) {
                if (Objects.equals(destClient, socketEntry.getValue())) {
                    Socket socket = socketEntry.getKey();
                    // 消息转发给其他客户端
                    OutputStream outputStream = socket.getOutputStream();
                    DataOutputStream dos = new DataOutputStream(outputStream);
                    // （因为不存在单独的私聊界面）使用群发条件，让信息显示在同一个群发界面上
                    dos.writeInt(1);
                    dos.writeUTF(msg);
                    dos.flush();
                }
            }
        }

        /**
         * 发给所有客户端
         *
         * @param flag 标识
         * @param msg  消息
         * @throws IOException 异常
         */
        private void sendMsgToOthers(Flag flag, String msg) throws IOException {
            for (Socket otherSocket : onlineSocket.keySet()) {
                // 消息转发给其他客户端
                OutputStream outputStream = otherSocket.getOutputStream();
                DataOutputStream dos = new DataOutputStream(outputStream);
                dos.writeInt(flag.getFlag());
                dos.writeUTF(msg);
                dos.flush();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("服务端启动！");
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(Constant.PORT);
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
