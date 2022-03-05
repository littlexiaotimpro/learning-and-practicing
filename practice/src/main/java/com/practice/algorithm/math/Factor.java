package com.practice.algorithm.math;

import java.util.Scanner;

/**
 * 分解质因数
 *
 * @date 2022/3/5
 */
public class Factor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long n = scanner.nextLong();
        for (int i = 2; (long) i * i <= n; i++) {
            if (n % i == 0) {
                while (n % i == 0) {
                    System.out.print(i + " ");
                    n /= i;
                }
            }
        }
        if (n > 1) System.out.print(n);
    }
}
