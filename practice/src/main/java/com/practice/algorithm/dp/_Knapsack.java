package com.practice.algorithm.dp;

import java.util.*;

/**
 * 动态规划背包问题
 * 题目描述：
 * 王强今天很开心，公司发给N元的年终奖。
 * 王强决定把年终奖用于购物，他把想买的物品分为两类：
 * 主件与附件，附件是从属于某个主件的，下表就是一些主件与附件的例子：
 * 主件	    附件
 * 电脑	    打印机，扫描仪
 * 书柜	    图书
 * 书桌	    台灯，文具
 * 工作椅	无
 * 如果要买归类为附件的物品，必须先买该附件所属的主件。
 * 每个主件可以有 0 个、 1 个或 2 个附件。附件不再有从属于自己的附件。
 * 王强想买的东西很多，为了不超出预算，他把每件物品规定了一个重要度，分为 5 等：用整数 1 ~ 5 表示，第 5 等最重要。
 * 他还从因特网上查到了每件物品的价格（都是 10 元的整数倍）。他希望在不超过 N 元（可以等于 N 元）的前提下，
 * 使每件物品的价格与重要度的乘积的总和最大。
 * 设第 j 件物品的价格为 v[j] ，重要度为 w[j] ，共选中了 k 件物品，编号依次为 j 1 ， j 2 ，……， j k ，
 * 则所求的总和为：v[j 1 ]*w[j 1 ]+v[j 2 ]*w[j 2 ]+ … +v[j k ]*w[j k ] 。（其中 * 为乘号）
 *
 * @date 2020/9/9
 */
public class _Knapsack {


    /**
     * dp 求解
     *
     * @param sources 物品集合
     * @param m       预算
     * @param n       物品数
     * @return res
     */
    public static Integer loopResolver(List<List<Integer>> sources, int m, int n) {
        if (n <= 0 || m <= 0) return 0;
        // 预算和value都是10的倍数，所以节省内存，都缩10倍
        int col = m / 10 + 1;
        int row = n + 1;
        int[][] dp = new int[n + 1][col];
        // 填充其他值
        for (int i = 1; i < row; i++) {
            List<Integer> list = sources.get(i - 1);
            // 当前物品价值
            Integer value = list.get(0);
            // 当前物品权重
            Integer weight = list.get(1);
            // 当前物品所属的主件索引（0: 主件，!0: 附件）
            Integer tag = list.get(2);
            for (int j = 1; j < col; j++) {
                if (tag == 0) {
                    if (value / 10 > j) {
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        dp[i][j] = Math.max(
                                dp[i - 1][j],
                                dp[i - 1][j - value / 10] + value * weight
                        );
                    }
                } else {
                    // 附件对应主件
                    List<Integer> mv = sources.get(tag - 1);
                    Integer mValue = mv.get(0);
                    Integer mWeight = mv.get(1);
                    if ((value + mValue) / 10 > j) {
                        // 如果附件及主件价值之和当前预算
                        dp[i][j] = dp[i - 1][j];
                    } else {
                        int a = dp[i - 1][j];
                        // TODO 如果购买当前附件，则同时购买主件（对于附件，若主件已经购买，则无需重复购买）
                        int b = dp[i - 1][j - value / 10 - mValue / 10] + value * weight + mValue * mWeight;
                        dp[i][j] = Math.max(a, b);
                    }
                }
            }
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        return dp[n][m / 10];
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // 输入金额，购买的最大物件数，【M,N】
        String[] split = input.nextLine().split("\\s+");
        int m = Integer.parseInt(split[0]);
        int n = Integer.parseInt(split[1]);
        List<List<Integer>> sources = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String[] strings = input.nextLine().split("\\s+");
            List<Integer> list = new ArrayList<>();
            for (String string : strings) {
                list.add(Integer.parseInt(string));
            }
            sources.add(list);
        }
        Integer integer = loopResolver(sources, m, n);
        System.out.println(integer);
    }
}
