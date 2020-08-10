package com.practice.algorithm.sort;

import java.util.Arrays;

/**
 * 归并排序：
 * 1.二路归并，将两个有序的子序列合并成一个有序序列
 * 2.多路归并，不断执行二路归并的过程
 * 2.1.自底向上，以一个元素为一个序列开始慢慢递增，直到最终两个序列走二路归并过程
 * 2.2.自顶向下
 */
public class _MergeSort {


    /**
     * 二路归并的过程
     *
     * @param source 元序列
     * @param target 目标序列
     * @param left   左子序列开始位置
     * @param mid    左子序列结束位置，+1 为右子序列开始位置，mid <= n-1
     * @param right  右子序列结束位置, right <= n-1
     */
    private static void doubleMerge(int[] source, int[] target, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        while (i <= mid && j <= right) {
            if (source[i] <= source[j]) {
                target[k++] = source[i++];
            } else {
                target[k++] = source[j++];
            }
        }
        while (i <= mid) {
            target[k++] = source[i++];
        }
        while (j <= right) {
            target[k++] = source[j++];
        }
    }

    /**
     * 归并排序 - 自底向上
     * 以一个元素为序列，两两进行归并
     * 以第一次合并后的2个元素为序列，再两两归并
     * ...
     * 最终两个有序子序列做归并得到排序结果
     * <p>
     * 时间复杂度：O(n*log(n))
     * 空间复杂度：O(n)
     * 稳定排序，非原地排序
     */
    private static void mergeSortToUp(int[] source, int[] target) {
        int length = source.length;
        for (int increment = 1; increment < length; increment *= 2) {
            // i 的增量为当前归并子序列的长度的2倍
            for (int i = 0; i < length; i += 2 * increment) {
                // mid <= length - 1
                // right <= length -1
                doubleMerge(source, target, i, Math.min(i + increment - 1, length - 1), Math.min(i + 2 * increment - 1, length - 1));
            }
            // 数组拷贝
            arrayCopy(target, 0, source, length);
        }
    }

    /**
     * 归并排序 - 自顶向下
     * 采用递归的方式，先将整个序列分为两个子序列
     * 两个子序列分别再分为两个子序列，此时为 4 个子序列
     * ...
     * 此时每个子序列只有一个元素，无法再分，开始两两归并
     */
    private static void mergeSortToLow(int[] source, int[] target, int left, int right) {
        if (left == right) {
            target[left] = source[right];
            return;
        }
        // 递归
        mergeSortToLow(source, target, left, (left + right) / 2);
        mergeSortToLow(source, target, (left + right) / 2 + 1, right);
        // 归并
        doubleMerge(target, source, left, (left + right) / 2, right);
        // 复制
        arrayCopy(source, left, target, right + 1);
    }

    /**
     * 数组复制
     */
    private static void arrayCopy(int[] source, int srcPos, int[] target, int length) {
        for (int i = srcPos; i < length; i++) {
            target[i] = source[i];
        }
    }

    public static void main(String[] args) {
        int[] source = {5, 9, 3, 2, 4, 9, 1, 0, 0, 1, 34, 6};
        System.out.println("排序前 => " + Arrays.toString(source));
        int[] target = new int[source.length];
        mergeSortToUp(source, target);
        // 1.归并排序 - 自底向上
        System.out.println("归并排序，自底向上 => " + Arrays.toString(target));

        source = new int[]{5, 9, 3, 2, 4, 9, 1, 0, 0, 1, 34, 6};
        target = new int[source.length];
        mergeSortToLow(source, target, 0, source.length - 1);
        // 2.归并排序 - 自顶向下
        System.out.println("归并排序，自顶向下 => " + Arrays.toString(target));
    }

}
