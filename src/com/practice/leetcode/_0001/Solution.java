package com.practice.leetcode._0001;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/8/1321:31
 */
public class Solution {

    /**
     * 使用Map集和保存数据，将数组值作为 `key`，下标作为 `value`
     * 在此基础上，对原数组进行遍历，每一次遍历判断目标值与当前数值差
     * 即 `key` 是否存在，此时需要排除下标是当前值下标的结果
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            if (map.get(target - nums[i]) != null && map.get(target - nums[i]) != i) {
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        throw new IllegalArgumentException("No two sum solution");
    }

}
