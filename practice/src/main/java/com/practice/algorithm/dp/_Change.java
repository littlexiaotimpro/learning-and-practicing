package com.practice.algorithm.dp;

/**
 * 使用不同的零钱组合出金额
 * 每个零钱可以使用无限次
 * 动态规划求解
 */
public class _Change {
    public int change(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        _Change change = new _Change();
        int r = change.change(5, new int[]{1, 2, 5});
        System.out.println(r);
    }
}
