package com.practice.algorithm.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 基数排序
 */
public class _RadixSort {

    /**
     * 基数排序 - LSD
     * 从最低位开始对元素进行排序操作
     * 一次遍历结束后，将遍历结果拷贝回原数组，重复进行操作
     * 当最高位遍历结束后，将容器中结果按顺序输出，得到有序序列
     * 时间复杂度：O(n*k)，k，为最大的数字位数
     * 空间复杂度：O(n+k)，k = 10
     * 稳定排序，非原地排序
     */
    private static void radixSortLow(int[] source, int n){
        int max = source[0];
        for (int i = 1; i < n; i++) {
            if (source[i] > max) {
                max = source[i];
            }
        }
        // 位数值范围：[0, 9]，每一个索引位上一个链表
        List<ArrayList<Integer>> tran = new ArrayList<>(10);
        for (int i = 0; i < 10; i++) {
            tran.add(new ArrayList<>());
        }
        // 用于求解各个位数的值
        int count = 0;
        while (count >= 0) {
            // 最高位已经处理之后，结束循环
            if ((int) (max / Math.pow(10, count)) % 10 == 0) {
                break;
            }
            for (int i = 0; i < n; i++) {
                // 各个位数值
                int index = (int) (source[i] / Math.pow(10, count)) % 10;
                tran.get(index).add(source[i]);
            }
            int k = 0;
            for (ArrayList<Integer> items : tran) {
                if (!items.isEmpty()) {
                    for (Integer item : items) {
                        source[k++] = item;
                    }
                    items.clear();
                }
            }
            count++;
        }
    }

    /**
     * 基数排序 - MSD
     * 从最高位开始遍历求解
     * 时间复杂度：O(n*k)，k，为最大的数字位数
     * 空间复杂度：O(n+k)，k = 10
     * 稳定排序，非原地排序
     */
    private static void radixSortHigh(int[] source, int n){
        int max = source[0];
        for (int i = 1; i < n; i++) {
            if (source[i] > max) {
                max = source[i];
            }
        }
        // 用于求解各个位数的值
        int count = 0;
        while (max / 10 > 0) {
            count++;
            max = max / 10;
        }
        radixSort(source, n, count);
    }

    // TODO 最高位求解
    private static void radixSort(int[] source, int n, int bit) {
    }

    public static void main(String[] args) {
        int[] source = {5, 9, 3, 2, 4, 9, 1, 0, 0, 1, 34, 6};
        System.out.println("排序前 => " + Arrays.toString(source));
        // 基数排序
        source = new int[]{5, 9, 3, 2, 4, 9, 1, 0, 0, 1, 34, 6};
        radixSortLow(source, source.length);
        System.out.println("基数排序，最低位求解 => " + Arrays.toString(source));
        source = new int[]{5, 9, 949, 3, 212, 444, 9, 1, 23, 26, 1, 34, 0, 6};
        radixSortHigh(source, source.length);
        System.out.println("基数排序，最高位求解 => " + Arrays.toString(source));
    }

}
