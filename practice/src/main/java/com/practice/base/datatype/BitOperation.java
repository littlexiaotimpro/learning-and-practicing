package com.practice.base.datatype;

/**
 * 位运算
 */
public class BitOperation {

    public static void main(String[] args) {
        //  5: 00000101
        // -5: 11111011
        int a = 5 & 1;
        int b = 5 | 1;
        int c = 5 ^ 1;
        int d = ~5;
        int e = -5 >> 1;
        int f = -5 << 2;
        int g = -5 >>> 31;
        int h = 5 >>> 2;

        System.out.println("a -> " + a);
        System.out.println("b -> " + b);
        System.out.println("c -> " + c);
        System.out.println("d -> " + d);
        System.out.println("e -> " + e);
        System.out.println("f -> " + f);
        System.out.println("g -> " + g);
        System.out.println("h -> " + h);
    }
}
