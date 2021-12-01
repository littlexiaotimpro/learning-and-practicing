package com.practice.algorithm.backtracking;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 全排列
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2], [1,2,1], [2,1,1]]
 */
public class Permutations {

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        backtrack(nums, new LinkedList<>(), res, new ArrayList<>());
        return res;
    }

    private void backtrack(int[] nums, LinkedList<Integer> tran, List<List<Integer>> res, List<Integer> choose) {
        if (nums.length == 0) {
            res.add(new LinkedList<>(tran));
            return;
        }
        List<List<Integer>> reTable = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (choose.contains(i)) continue;
            // 做选择
            tran.add(nums[i]);
            choose.add(i);
            // 若 reTable 中存在相同前缀的集合，则跳过
            if (check(reTable, tran)) {
                tran.removeLast();
                continue;
            } else {
                reTable.add(tran);
            }
            // 进入下一层决策树
            backtrack(nums, tran, res, choose);
            choose.remove(choose.size()-1);
            // 取消选择
            tran.removeLast();
        }
    }

    private boolean check(List<List<Integer>> reTable, LinkedList<Integer> tran){
        for (List<Integer> integers : reTable) {
            if (integers.equals(tran)){
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Permutations permutations = new Permutations();
        int[] nums = new int[]{1, 1, 2};
        List<List<Integer>> lists = permutations.permuteUnique(nums);
        System.out.println(lists);
    }

}
