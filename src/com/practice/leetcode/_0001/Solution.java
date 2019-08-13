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
        return null;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] num = new int[]{2, 7, 11, 15};
        int target = 29;
        int[] result = solution.twoSum(num, target);
        if (result==null) return;
        for (int item : result
                ) {
            System.out.println(item);
        }
    }

}
