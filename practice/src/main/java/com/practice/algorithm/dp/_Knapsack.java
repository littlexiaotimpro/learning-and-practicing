package com.practice.algorithm.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author XiaoSi
 * @className _Knapsack
 * @description 动态规划背包问题
 * @date 2020/9/9
 */
public class _Knapsack {
    /**
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
     */

    /**
     * 递归解法
     */
    public static Integer loopResolver(List<List<Integer>> sources, int m, int n) {
        if (n<=0||m<=0) return 0;
        Integer[] res = new Integer[n];
        for (int i = 0; i < sources.size(); i++) {
            List<Integer> list = sources.get(i);
            Integer value = list.get(0);
            Integer weight = list.get(1);
            Integer tag = list.get(2);
            if (i == 0 && tag == 0) {
                res[i] = value * weight;
            } else {
                res[i] = Math.max(res[i - 1], res[i - 1] + value * weight);
            }
        }

        return res[n-1];
    }

    /**
     * 1000 5
     * 800 2 0
     * 400 5 1
     * 300 5 1
     * 400 3 0
     * 500 2 0
     */
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // 输入金额，购买的最大物件数，【M,N】
        String[] split = input.nextLine().split("\\s+");
        int m = Integer.parseInt(split[0]);
        int n = Integer.parseInt(split[1]);
        List<List<Integer>> sources = new ArrayList<>();
        for (int i=0;i<n;i++){
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
