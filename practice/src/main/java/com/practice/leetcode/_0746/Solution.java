package com.practice.leetcode._0746;

public class Solution {

    private static int minCostClimbingStairs(int[] cost) {
        if(cost.length==2){
            return Math.min(cost[0],cost[1]);
        }
        int n = cost.length;
        int result0 = 0;
        int result1 = 0;
        for (int i = n - 1; i >= 0; --i) {
            int f0 = cost[i] + Math.min(result0, result1);
            result1 = result0;
            result0 = f0;
        }
        return Math.min(result0, result1);
    }

    public static void main(String[] args) {
        int[] cost = {0,1,2,2};
        System.out.println(minCostClimbingStairs(cost));
    }

}
