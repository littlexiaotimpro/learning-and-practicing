package com.practice.algorithm.dp;

import java.util.Arrays;

/**
 * @author XiaoSi
 * @className _Subsequence
 * @description 给定序列中的最长上升子序列
 * @date 2020/11/11
 */
public class _Subsequence {

    /**
     * 查找最长上升子序列 ( Longest Increase Subsequence )
     * 假设最长子序列的最后最大值索引为 x ( x > 1)，那么可以推测出存在一个值 p (p < x)，
     * 使得 source[p] < source[x]，即，f(x) = f(p) + 1，f(n)表示最优解
     * <p>
     * 一般式为，f(x) = max(f(p)) + 1，p < x，且 source[p] < source[x]
     * <p>
     * 时间复杂度：O(n^2) -> O(n*log(n))
     * 空间复杂度：O(n)
     */
    private static int findLIS(int[] source, int n) {
        if (n == 0) {
            return 0;
        }
        int[] res = new int[n];
        // 赋初始值
        Arrays.fill(res, 1);
        int max = 1;
        for (int i = 1; i < n; i++) {
            // 内循环目的是求最后以第i各元素结尾的最长上升子序列
            for (int j = i - 1; j >= 0; j--) {
                if (source[j] < source[i]) {
                    res[i] = Math.max(res[j] + 1, res[i]);
                }
            }
            max = Math.max(max, res[i]);
        }
        // 输出各元素值对应位置的最优解
        System.out.println(Arrays.toString(res));
        return max;
    }

    /**
     * 保持字符相对位置不变，判断 s 字符串是否在 t 字符串中
     *
     * @param s 模式字符组
     * @param t 目标字符组
     */
    private static boolean hasSubStr(String s, String t) {
        if (s.length() > t.length()) {
            return false;
        }
        char[] chars = s.toCharArray();
        int cursor = 0;
        for (int i = 0; i < t.length(); i++) {
            if (cursor < chars.length && t.charAt(i) == chars[cursor]) {
                cursor++;
            }
        }
        return cursor == chars.length;
    }


    public static void main(String[] args) {
        int[] source = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        int lis = findLIS(source, source.length);
        System.out.println("最长上升子序列长度为：" + lis);
        System.out.println(hasSubStr("b", "abc"));
    }

}
