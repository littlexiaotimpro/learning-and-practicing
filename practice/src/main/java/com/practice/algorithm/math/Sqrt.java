package com.practice.algorithm.math;

/**
 * 开根号
 *
 * @author chen.hong
 * @date 2022/3/10
 */
public class Sqrt {
    public static void main(String[] args) {
        System.out.println(sqrt(10022));
        System.out.println(sqrt(65536));
        System.out.println(sqrt(24124));
        System.out.println((int) Math.sqrt(10022));
        System.out.println((int) Math.sqrt(65536));
        System.out.println((int) Math.sqrt(24124));
        System.out.println(100 * 100);
        System.out.println(155 * 155);
    }

    private static int sqrt(int x) {
        int i = 1;
        for (; i * i <= x; i++) {

        }
        return i - 1;
    }
}
