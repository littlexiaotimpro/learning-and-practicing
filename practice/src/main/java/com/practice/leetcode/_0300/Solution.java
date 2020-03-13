package com.practice.leetcode._0300;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author XiaoSi
 * @className Solution
 * @description 最长上升子序列
 * @date 2020/3/14
 */
public class Solution {
    public int lengthOfLIS(int[] nums) {
        Set<Integer> set = new HashSet<Integer>();
        int result = 0;
        int curr = 0;
        for(int i=0;i<nums.length;i++){
            curr = nums[i];
            Iterator<Integer> iter = set.iterator();
            while(iter.hasNext()){
                if(iter.next() >curr){
                    iter.remove();
                }
            }
            set.add(curr);
            for(int j=i+1;j<nums.length;j++){
                if(nums[j]>=curr){
                    curr = nums[j];
                    set.add(curr);
                }
            }
            result = Math.max(result, set.size());
        }
        return result;
    }
}
