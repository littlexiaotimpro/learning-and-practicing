package com.practice.io.bio.demo07;

import com.practice.io.bio.demo07.constant.Constant;
import com.practice.io.bio.demo07.constant.Flag;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

/**
 * BIO 模式下的端口转发: 实现多客户端之间的消息收发机制（发送消息）
 *
 * @date 2022/2/27
 */
public class ClientDemo07 implements ActionListener {
    // 设计界面
    private JFrame win = new JFrame();
    // 消息内容展示区
    private JTextArea smsContent = new JTextArea(24, 50);
    // 信息输入区
    private JTextArea smsSend = new JTextArea(4, 40);
    // 在线客户端展示区
    public JList<String> onlineClients = new JList<>();

    // 选择是否私聊
    private JCheckBox isPrivateBtn = new JCheckBox("private");
    // 消息发送按钮
    private JButton sendBtn = new JButton("send");

    // 登录界面
    private JFrame loginView;
    private JTextField ipEt, nameEt, idEt;
    private Socket socket;

    public static void main(String[] args) {
        new ClientDemo07().initView();
    }

    /**
     * 初始化界面
     */
    private void initView() {
        // 初始化聊天主界面
        win.setSize(650, 600);
        // 登录
        openLoginView();
    }

    private void openLoginView() {
        loginView = new JFrame("登录");
        loginView.setLayout(new GridLayout(3, 1));
        loginView.setSize(400, 200);

        JPanel ip = new JPanel();
        JLabel label = new JLabel("ip: ");
        ip.add(label);
        ipEt = new JTextField(20);
        ipEt.setText("127.0.0.1");
        ip.add(ipEt);
        loginView.add(ip);

        JPanel name = new JPanel();
        JLabel nLabel = new JLabel("name: ");
        name.add(nLabel);
        nameEt = new JTextField(20);
        name.add(nameEt);
        loginView.add(name);

        JPanel btnView = new JPanel();
        JButton login = new JButton("login");
        JButton cancel = new JButton("cancel");
        btnView.add(login);
        btnView.add(cancel);
        loginView.add(btnView);
        // 关闭窗口退出程序
        loginView.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setWinCenter(loginView, 400, 200, true);

        login.addActionListener(this);
        cancel.addActionListener(this);
    }

    private void setWinCenter(JFrame jFrame, int width, int height, boolean flag) {
        Dimension ds = jFrame.getToolkit().getScreenSize();
        int w = ds.width;
        int h = ds.height;
        System.out.println(w + "/" + h);
        System.out.println((w - width) / 2 + "/" + (h - height) / 2);
        jFrame.setLocation((w - width) / 2, (h - height) / 2);
        jFrame.setVisible(flag);
    }

    private void openChatView() {
        JPanel bottomPanel = new JPanel(new BorderLayout());

        win.add(bottomPanel, BorderLayout.SOUTH);
        bottomPanel.add(smsSend);
        JPanel btns = new JPanel(new FlowLayout(FlowLayout.LEFT));
        btns.add(sendBtn);
        btns.add(isPrivateBtn);
        bottomPanel.add(btns, BorderLayout.EAST);

        smsContent.setBackground(Color.gray);
        win.add(new JScrollPane(smsContent), BorderLayout.CENTER);
        smsContent.setEditable(false);

        Box rightBox = new Box(BoxLayout.Y_AXIS);
        onlineClients.setFixedCellHeight(120);
        onlineClients.setVisibleRowCount(30);
        rightBox.add(new JScrollPane(onlineClients));
        win.add(rightBox, BorderLayout.EAST);

        win.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        win.pack();
        setWinCenter(win, 650, 1000, true);

        sendBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton btn = (JButton) e.getSource();
        switch (btn.getText()) {
            case "login":
                String ip = ipEt.getText();
                String name = nameEt.getText();
                // TODO login check
                try {
                    win.setTitle(name);
                    socket = new Socket(ip, Constant.PORT);
                    // 接收消息
                    new CThread(this, socket).start();
                    // 发送消息
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeInt(Flag.LOGIN.getFlag());
                    dos.writeUTF(name);
                    dos.flush();

                    // 关闭当前窗口
                    loginView.dispose();
                    // 进入聊天界面
                    openChatView();
                } catch (IOException unknownHostException) {
                    unknownHostException.printStackTrace();
                }
                break;
            case "cancel":
                loginView.dispose();
                System.exit(0);
                break;
            case "send":
                String msg = smsSend.getText();
                // TODO msg requiredNonNull
                try {
                    String selectedValue = onlineClients.getSelectedValue();
                    Flag flag = Flag.TO_ALL;
                    if (selectedValue != null && !selectedValue.isEmpty()) {
                        flag = Flag.TO_SOMEONE;
                        msg = "@" + selectedValue + ": " + msg;
                        if (isPrivateBtn.isSelected()) {
                            flag = Flag.TO_DEST_ONE;
                        }
                    }
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                    dos.writeInt(flag.getFlag());
                    dos.writeUTF(msg);
                    if (flag == Flag.TO_DEST_ONE) {
                        dos.writeUTF(selectedValue);
                    }
                    dos.flush();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
                smsSend.setText(null);
                break;
        }
    }

    public static class CThread extends Thread {
        private final ClientDemo07 client;
        private final Socket socket;

        public CThread(ClientDemo07 client, Socket socket) {
            this.client = client;
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                // 接收服务端转发到客户端的字节输入
                InputStream inputStream = socket.getInputStream();
                DataInputStream dis = new DataInputStream(inputStream);
                while (true) {
                    int flag = dis.readInt();
                    Flag flag1 = Flag.convert(flag);
                    if (flag1 == Flag.LOGIN) {
                        String clients = dis.readUTF();
                        String[] cs = clients.split(Constant.SPLIT);
                        client.onlineClients.setListData(cs);
                    } else {
                        String msg = dis.readUTF();
                        client.smsContent.append(msg);
                        client.smsContent.setCaretPosition(client.smsContent.getText().length());
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
