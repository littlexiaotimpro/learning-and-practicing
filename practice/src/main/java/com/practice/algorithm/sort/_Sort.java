package com.practice.algorithm.sort;

import java.util.Arrays;

/**
 * 排序算法
 */
public class _Sort {

    /**
     * 选择排序
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     * 非稳定排序，原地排序
     *
     * @param source 目标数组
     * @return 排序后的数组
     */
    private static int[] selectSort(int[] source) {
        if (source == null || source.length <= 1) {
            return source;
        }
        for (int i = 0; i < source.length; i++) {
            int index = i;
            // 从头开始遍历，排序子序列位于整个数组的头部
            // 每一次比较确定一个最小值的索引，最后将最小索引位与当前位置的值进行替换
            for (int j = i + 1; j < source.length; j++) {
                if (source[j] < source[index]) {
                    index = j;
                }
            }
            if (index != i) {
                int min = source[i];
                source[i] = source[index];
                source[index] = min;
            }
        }
        return source;
    }

    /**
     * 冒泡排序
     * 时间复杂度：O(n2)
     * 空间复杂度：O(1)
     * 稳定排序，原地排序
     *
     * @param source 目标数组
     * @return 排序后的数组
     */
    private static int[] bubbleSort(int[] source) {
        if (source == null || source.length <= 1) {
            return source;
        }
        for (int i = 0; i < source.length - 1; i++) {
            // 从头开始的遍历操作，尾部序列是已经排好序的子序列
            // 每一次比较相邻两个位置的元素大小，较大值后移，重复此操作
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
     *
     * @param source 目标数组
     * @return 排序后的数组
     */
    private static int[] insertSort(int[] source) {
        if (source == null || source.length <= 1) {
            return source;
        }
        for (int i = 1; i < source.length; i++) {
            int curr = source[i];
            int j = i - 1;
            // 从头开始的遍历操作，头部序列是已经排好序的子序列
            // 寻找目标值在有序子序列中的插入位置，子序列相应元素每次遍历依次向后位移
            for (; j >= 0 && curr < source[j]; j--) {
                source[j + 1] = source[j];
            }
            source[j + 1] = curr;
        }
        return source;
    }

    /**
     * 希尔排序
     * 时间复杂度：O(n*log2(n))
     * 空间复杂度：O(1)
     * 非稳定排序，原地排序
     *
     * @param source 目标数组
     * @return 排序后的数组
     */
    private static int[] shellSort(int[] source) {
        if (source == null || source.length <= 1) {
            return source;
        }
        int length = source.length;
        int increment = length >> 1;
        while (increment >= 1) {
            for (int i = 0; i < increment; i++) {
                for (int j = i + increment; j < length; j += increment) {
                    // 内部逻辑为插入排序
                    // 每次元素的跨度为当前的增量值
                    int curr = source[j];
                    int k = j - increment;
                    for (; k >= 0 && curr < source[k]; k -= increment) {
                        source[k + increment] = source[k];
                    }
                    source[k + increment] = curr;
                }
            }
            increment = increment >> 1;
        }
        return source;
    }

    /**
     * 1s = 1000ms
     * 1ms = 10^6ns
     */
    public static void main(String[] args) {
        int[] source = {5, 9, 3, 2, 4, 9, 1, 0, 0, 1, 34, 6};
        System.out.println("排序前 => " + Arrays.toString(source));
        //1.选择排序
        System.out.println("选择排序 => " + Arrays.toString(selectSort(source)));
        //2.冒泡排序
        source = new int[]{5, 9, 3, 2, 4, 9, 1, 0, 0, 1, 34, 6};
        System.out.println("冒泡排序 => " + Arrays.toString(bubbleSort(source)));
        //3.插入排序
        source = new int[]{5, 9, 3, 2, 4, 9, 1, 0, 0, 1, 34, 6};
        System.out.println("插入排序 => " + Arrays.toString(insertSort(source)));
        //4.希尔排序
        source = new int[]{5, 9, 3, 2, 4, 9, 1, 0, 0, 1, 34, 6};
        System.out.println("希尔排序 => " + Arrays.toString(shellSort(source)));
    }
}
