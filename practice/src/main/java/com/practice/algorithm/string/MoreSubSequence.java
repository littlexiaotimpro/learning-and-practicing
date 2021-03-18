package com.practice.algorithm.string;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode:求目标字符串中最多含有多少各模式串子序列
 * s:babgbag，t:bag
 * more：5[b1a1g1,b1a1g2,b1a2g2,b2a2g2,b3a2g2]
 */
public class MoreSubSequence {

    /**
     * 回溯解：超时
     */
    private static int moreSubSequence01(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m < n) return 0;
        if (s.equals(t) || m > 0 && t.isEmpty()) return 1;

        StringBuilder builder = new StringBuilder();
        int i = 0;
        int res = 0;
        List<Integer> tran = new ArrayList<>();
        while (i < m) {
            if (s.charAt(i) == t.charAt(builder.length())) {
                tran.add(i);
                builder.append(s.charAt(i));
                if (builder.toString().equals(t)) {
                    /* 当字符串匹配时，此时移除最后一个字符并回溯字符串的匹配位继续查找下一个节点 */
                    builder.delete(builder.length() - 1, builder.length());
                    /* 目标串回溯位置 */
                    i = tran.remove(tran.size() - 1);
                    res += 1;
                }
            }
            /* 目标串已经遍历完成此时，继续回溯字符匹配位置，匹配其它结果 */
            while (i == m - 1 && !tran.isEmpty()) {
                builder.delete(builder.length() - 1, builder.length());
                i = tran.remove(tran.size() - 1);
            }
            i++;
        }
        return res;
    }

    /**
     * 动态规划解
     * s:m, t:n;
     * def: dp[m+1][n+1];
     * j=n, t[j]='', dp[i][n] = 1;
     * i=m, s[i]='', dp[m][j] = 0, dp[m][n] = 1;
     * i<m, j<n
     * s[i] = t[j], dp[i][j] = dp[i + 1][j] + dp[i + 1][j + 1];
     * s[i] != t[j], dp[i][j] = dp[i + 1][j];
     */
    private static int dp(String s, String t) {
        int m = s.length();
        int n = t.length();
        if (m < n) return 0;
        if (s.equals(t) || m > 0 && t.isEmpty()) return 1;

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][n] = 1;
        }
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (s.charAt(i) == t.charAt(j)) {
                    dp[i][j] = dp[i + 1][j] + dp[i + 1][j + 1];
                } else {
                    dp[i][j] = dp[i + 1][j];
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 递归解
     */
    private static int recursion(String s, String t) {
        return 0;
    }

    public static void main(String[] args) {
//        System.out.println(moreSubSequence01("rabbibbit", "rabbit"));
        System.out.println(dp("rabbbit", "rabbit"));
    }
}
