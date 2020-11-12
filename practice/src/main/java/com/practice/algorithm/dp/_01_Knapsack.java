package com.practice.algorithm.dp;

import java.util.Arrays;

/**
 * @author XiaoSi
 * @className _01_Knapsack
 * @description 动态规划【01】背包问题
 * @date 2020/9/9
 */
public class _01_Knapsack {
    /**
     * leetcode: 474
     * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
     * 请你找出并返回 strs 的最大子集的大小，该子集中 最多 有 m 个 0 和 n 个 1 。
     * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
     * 输入：strs = ["10", "0001", "111001", "1", "0"], m = 5, n = 3
     * 输出：4
     */
    private static int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String s: strs) {
            int[] count = countZeroesOnes(s);
            for (int zeroes = m; zeroes >= count[0]; zeroes--)
                for (int ones = n; ones >= count[1]; ones--)
                    dp[zeroes][ones] = Math.max(1 + dp[zeroes - count[0]][ones - count[1]], dp[zeroes][ones]);
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        return dp[m][n];
    }
    private static int[] countZeroesOnes(String s) {
        int[] c = new int[2];
        for (int i = 0; i < s.length(); i++) {
            c[s.charAt(i)-'0']++;
        }
        return c;
    }

    public static void main(String[] args) {
        String[] sources = {"10", "0001", "111001", "1", "0"};
        int maxForm = findMaxForm(sources, 5, 3);
        System.out.println(maxForm);
        maxForm = findMaxForm(sources, 3, 3);
        System.out.println(maxForm);
    }
}
