package com.practice.algorithm;

import java.util.Arrays;

/**
 * @author XiaoSi
 * @className _Sort
 * @description 排序
 * @date 2020/4/2
 */
public class _Sort {

    /**
     * 选择排序
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     * 非稳定排序，原地排序
     * @param source 目标数组
     * @return 排序后的数组
     */
    private static int[] selectSort(int[] source) {
        if (source == null || source.length <= 1) {
            return source;
        }
        for (int i = 0; i < source.length; i++) {
            for (int j = i + 1; j < source.length; j++) {
                if (source[j] < source[i]) {
                    int min = source[i];
                    source[i] = source[j];
                    source[j] = min;
                }
            }
        }
        return source;
    }

    /**
     * 1s = 1000ms
     * 1ms = 10^6ns
     */
    public static void main(String[] args) {
        int[] source = {5, 9, 3, 2, 4, 1, 0, 0, 1, 34, 6};
        //1.选择排序
        long selectT1 = System.nanoTime();
        System.out.println(Arrays.toString(selectSort(source)));
        long selectT2 = System.nanoTime();
        System.out.println((selectT2 - selectT1) / 1000000 + "ms");
    }
}
