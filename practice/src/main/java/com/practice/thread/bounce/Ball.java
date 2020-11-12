package com.practice.thread.bounce;

import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;

/**
 * @author XiaoSi
 * @className Ball
 * @description 窗体应用的实体对象
 * @date 2020/11/12
 */
public class Ball {

    private static final int X_SIZE = 15;
    private static final int Y_SIZE = 15;

    private double x = 0;
    private double y = 0;
    private double dx = 1;
    private double dy = 1;

    public void move(Rectangle2D bounds) {
        x += dx + Math.random();
        y += dy + Math.random();
        if (x < bounds.getMinX()) {
            x = bounds.getMinX();
            dx = -dx;
        }
        if (x + X_SIZE >= bounds.getMaxX()) {
            x = bounds.getMaxX() - X_SIZE;
            dx = -dx;
        }
        if (y < bounds.getMinY()) {
            y = bounds.getMinY();
            dy = -dy;
        }
        if (y + Y_SIZE >= bounds.getMaxY()) {
            y = bounds.getMaxX() - Y_SIZE;
            dy = -dy;
        }
    }

    public Ellipse2D getShape() {
        return new Ellipse2D.Double(x, y, X_SIZE, Y_SIZE);
    }
}
