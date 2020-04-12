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
            int index = i;
            for (int j = i + 1; j < source.length; j++) {
                if (source[j] < source[index]) {
                    index = j;
                }
            }
            int min = source[i];
            source[i] = source[index];
            source[index] = min;
        }
        return source;
    }

    /**
     * 冒泡排序
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     * 稳定排序，原地排序
     * @param source 目标数组
     * @return 排序后的数组
     */
    private static int[] bubbleSort(int[] source){
        if (source == null || source.length <= 1) {
            return source;
        }
        for (int i = 0; i < source.length - 1; i++) {
            for (int j = 0; j < source.length - 1 - i; j++) {
                if (source[j + 1] < source[j]) {
                    int min = source[j + 1];
                    source[j + 1] = source[j];
                    source[j] = min;
                }
            }
        }
        return source;
    }

    /**
     * 插入排序
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     * 稳定排序，原地排序
     * @param source 目标数组
     * @return 排序后的数组
     */
    private static int[] insertSort(int[] source){
        if (source == null || source.length <= 1) {
            return source;
        }
        for (int i = 1; i < source.length; i++) {
            int curr = source[i];
            int j = i - 1;
            for (; j >= 0 && curr < source[j]; j--) {
                source[j + 1] = source[j];

            }
            source[j + 1] = curr;
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
        System.out.println("选择排序 => " + Arrays.toString(selectSort(source)));
        //2.冒泡排序
        source = new int[]{5, 9, 3, 2, 4, 9,1, 0, 0, 1, 34, 6};
        System.out.println("冒泡排序 => " + Arrays.toString(bubbleSort(source)));
        //3.插入排序
        source = new int[]{5, 9, 3, 2, 4, 9,1, 0, 0, 1, 34, 6};
        System.out.println("插入排序 => " + Arrays.toString(insertSort(source)));
    }
}
