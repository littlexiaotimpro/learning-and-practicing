package com.practice.algorithm.sort;

import java.util.Arrays;

/**
 * 堆排序
 * 1.了解堆的结构
 * 2.如何使用堆进行排序
 *
 * 空间复杂度：原地排序，O(1)
 * 时间复杂度：O(n*log(n))
 */
public class _HeapSort {

    /**
     * 元素交换
     */
    private static void swap(int[] arr, int max, int i) {
        int temp = arr[max];
        arr[max] = arr[i];
        arr[i] = temp;
    }

    /**
     * 对每一个节点进行堆的结构转化
     * 堆的特点：完全二叉树，父节点比子节点大
     */
    private static void heapify(int[] arr, int n, int i) {
        if (i >= n) {
            return;
        }
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int max = i;
        if (left < n && arr[left] > arr[max]) {
            max = left;
        }
        if (right < n && arr[right] > arr[max]) {
            max = right;
        }
        if (max != i) {
            swap(arr, max, i);
            heapify(arr, n, max);
        }
    }

    /**
     * 通过最后一个节点获取将整个数组进行堆化
     * 若当前节点的索引为 i，则，父节点为 (i - 1)/2，左子节点为 (2i + 1)，右子节点为 (2i + 2)
     */
    private static void buildHeap(int[] arr, int n) {
        int last = n - 1;
        int parent = (last - 1) / 2;
        for (int i = parent; i >= 0; i--) {
            heapify(arr, n, i);
        }
    }

    /**
     * 堆排序，由于每次执行堆化操作后第一个元素一定为最大或最小值
     * 依据排序要求，替换首位元素的位置，再重复进行操作
     */
    private static void heapSort(int[] arr, int n) {
        buildHeap(arr, n);
        for (int i = n - 1; i >= 0; i--) {
            swap(arr, 0, i);
            heapify(arr, i, 0);
        }
    }

    public static void main(String[] args) {
        int[] tree = {4, 2, 5, 6, 1, 6, 3, 5};
        System.out.println("排序前 => " + Arrays.toString(tree));
        System.out.println();
        int n = tree.length;
        heapSort(tree, n);
        System.out.println("堆排序 => " + Arrays.toString(tree));
    }

}
