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
     */
    private static int findLIS(int[] source, int n) {
        if (n == 0) {
            return 0;
        }
        int[] res = new int[n];
        res[0] = 1;
        for (int i = 1; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (source[j] < source[i]) {
                    res[i] = Math.max(res[j] + 1, res[i]);
                }
            }
            if (res[i] == 0) {
                res[i] = 1;
            }
        }
        // 输出各元素值对应位置的最优解
        System.out.println(Arrays.toString(res));
        return res[n - 1];
    }


    public static void main(String[] args) {
        int[] source = {1, 5, 2, 3, 1, 6, 9, 7, 8};
        int lis = findLIS(source, source.length);
        System.out.println("最长上升子序列长度为：" + lis);
    }

}
