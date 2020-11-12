package com.practice.thread.bounce;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Bounce {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            JFrame frame = new BounceFrame();
            frame.setTitle("Bounce");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class BounceFrame extends JFrame {
    private final BallComponent comp;
    public static final int STEPS = 1000;
    public static final int DELAY = 5;

    public BounceFrame() {
        comp = new BallComponent();
        add(comp, BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        addButton(buttonPanel, "Start", event -> addBall());
        addButton(buttonPanel, "Close", event -> System.exit(0));
        add(buttonPanel, BorderLayout.SOUTH);
        pack();
    }

    public void addButton(Container c, String title, ActionListener listener) {
        JButton button = new JButton(title);
        c.add(button);
        button.addActionListener(listener);
    }

    /**
     * 主要线程相关逻辑
     */
    public void addBall() {
        Ball ball = new Ball();
        comp.add(ball);
        for (int i = 0; i < STEPS; i++) {
            ball.move(comp.getBounds());
            comp.paint(comp.getGraphics());
            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
