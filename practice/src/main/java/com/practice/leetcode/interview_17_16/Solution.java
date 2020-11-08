package com.practice.leetcode.interview_17_16;

/**
 * 动态规划求最大和
 * 在给定的数值数组中求非相邻元素的最大和，每一个元素都可以选或不选
 * 如，数组[1,2,4,3,6,2]，最大和为 11，【1,4,6】
 */
public class Solution {
    private static int massage(int[] nums) {
        int length = nums.length;
        int[] res = new int[length];
        if (length <= 0) {
            return 0;
        } else if (length == 1) {
            return nums[0];
        } else {
            res[0] = nums[0];
            res[1] = Math.max(nums[0], nums[1]);
            for (int i = 2; i < length; i++) {
                // 当前元素选或不选，res 表示已经确定的结果，比较两个结果值的大小
                // 选：res[i-2] + 当前元素值
                // 不选：res[i-1]
                res[i] = Math.max(res[i - 1], res[i - 2] + nums[i]);
            }
        }
        return res[length - 1];
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 4, 3, 6, 2};
        int massage = massage(nums);
        System.out.println(massage);
    }
}
