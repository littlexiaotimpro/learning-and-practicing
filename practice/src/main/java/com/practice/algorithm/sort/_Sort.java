package com.practice.algorithm.sort;

import java.util.*;

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
     * 快速排序
     * 时间复杂度：O(n*log2(n))
     * 空间复杂度：O(log2(n))
     * 非稳定排序，原地排序
     *
     * @param source 目标数组
     */
    private static void quickSort(int[] source, int left, int right) {
        if (left >= right) {
            return;
        }
        // 以左边第一个元素为标准
        int template = source[left];
        // 临时变量
        int l = left;
        int r = right;
        while (l < r) {
            // 两边向中间查找数据时，当左右变量的索引值一样时，则一次查找结束
            // 从右向左找一个小于比标准值的值，交换他们的位置
            while (l < r && source[r] >= template) {
                r--;
            }
            source[l] = source[r];
            // 从左向右找一个不小于标准值的值，交换他们的位置
            while (l < r && source[l] < template) {
                l++;
            }
            source[r] = source[l];
        }
        source[l] = template;
        // 左子序列排序
        quickSort(source, left, l - 1);
        // 右子序列排序
        quickSort(source, r + 1, right);
    }

    /**
     * 计数排序
     * 时间复杂度: O(n+k)，k 为临时数组的长度
     * 空间复杂度: O(k)
     * 稳定排序，非原地排序
     *
     * @param source 资源数组
     * @param n      数组长度
     */
    private static void countingSort(int[] source, int n) {
        int min = source[0];
        int max = source[0];
        for (int i = 1; i < n; i++) {
            if (source[i] > max) {
                max = source[i];
            }
            if (source[i] < min) {
                min = source[i];
            }
        }
        // 将元素值作为索引存入临时数组
        int[] tran = new int[max - min + 1];
        for (int i = 0; i < n; i++) {
            tran[source[i]] = ++tran[source[i]];
        }
        // 将临时数组中个元素的值返回至原数组，其中辅助数组的值表示索引元素出现的次数
        int k = 0;
        for (int i = 0; i < tran.length; i++) {
            if (tran[i] > 0) {
                int index = k + tran[i];
                // 按元素统计顺序逆序插入，保证排序算法的稳定性
                for (int j = 0; j < tran[i]; j++) {
                    source[--index] = i;
                    k++;
                }
            }
        }
    }

    /**
     * 桶排序
     * 将序列的最大最小值间隔划分等列的区间
     * 时间复杂度：O(n + k)
     * 空间复杂度：O(n + k)
     * 稳定排序，非原地排序
     *
     * @param source 资源数组
     * @param n      数组长度
     */
    private static void bucketSort(int[] source, int n) {
        int min = source[0];
        int max = source[0];
        for (int i = 1; i < n; i++) {
            if (source[i] > max) {
                max = source[i];
            }
            if (source[i] < min) {
                min = source[i];
            }
        }
        // 将元素值作为索引存入临时数组，若差值很大但元素不多，会浪费很多空间
        // 优化后的初始容量
        int initialCapacity = (max - min) / 10 + 1;
        List<LinkedList<Integer>> tran = new ArrayList<>(initialCapacity);
        for (int i = 0; i < initialCapacity; i++) {
            tran.add(new LinkedList<>());
        }
        for (int i = 0; i < n; i++) {
            int index = (source[i] - min) / 10;
            LinkedList<Integer> item = tran.get(index);
            // 已有元素的做排序操作
            int insertIndex = item.size();
            for (int j = insertIndex - 1; j >= 0 && item.get(j) > source[i]; j--) {
                insertIndex = j;
            }
            item.add(insertIndex, source[i]);
        }
        int k = 0;
        for (LinkedList<Integer> integers : tran) {
            if (integers.size() > 0) {
                for (Integer integer : integers) {
                    source[k++] = integer;
                }
            }
        }
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
        //5.快速排序
        source = new int[]{5, 9, 3, 2, 4, 9, 1, 0, 0, 1, 34, 6};
        quickSort(source, 0, source.length - 1);
        System.out.println("快速排序 => " + Arrays.toString(source));

        //6.计数排序
        source = new int[]{5, 9, 3, 2, 4, 9, 1, 0, 0, 1, 34, 6};
        countingSort(source, source.length);
        System.out.println("计数排序 => " + Arrays.toString(source));
        //7.桶排序
        source = new int[]{5, 9, 3, 2, 4, 9, 1, 0, 0, 1, 34, 6};
        bucketSort(source, source.length);
        System.out.println("桶排序 => " + Arrays.toString(source));
    }
}
