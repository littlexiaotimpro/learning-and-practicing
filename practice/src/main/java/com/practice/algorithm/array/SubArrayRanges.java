package com.practice.algorithm.array;

/**
 * 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
 * 返回 nums 中 所有 子数组范围的 和 。
 * 子数组是数组中一个连续 非空 的元素序列。
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：4
 * 解释：nums 的 6 个子数组如下所示：
 * [1]，范围 = 最大 - 最小 = 1 - 1 = 0
 * [2]，范围 = 2 - 2 = 0
 * [3]，范围 = 3 - 3 = 0
 * [1,2]，范围 = 2 - 1 = 1
 * [2,3]，范围 = 3 - 2 = 1
 * [1,2,3]，范围 = 3 - 1 = 2
 * 所有范围的和是 0 + 0 + 0 + 1 + 1 + 2 = 4
 *
 * @date 2022/3/4
 */
public class SubArrayRanges {

    public static long subArrayRanges(int[] nums) {
        int l = nums.length;
        if (l == 1) return 0;
        long res = 0;
        int i = 0;
        int j = 0;
        int max = nums[0];
        int min = nums[0];
        while (i <= j) {
            max = Math.max(nums[j], max);
            min = Math.min(nums[j], min);
            res += max - min;
            j++;
            if (j >= l) {
                i++;
                if (i >= l) break;
                j = i;
                max = nums[i];
                min = nums[j];
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{4, -2, -3, 4, 1};
        long res = subArrayRanges(nums);
        System.out.println(res);
    }

}
