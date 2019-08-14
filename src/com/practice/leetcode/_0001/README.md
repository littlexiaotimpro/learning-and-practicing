# 1. Two Sum

<span style="color:green">**Easy**</span>

Given an array of integers, return indices of the two numbers such that they add up to a specific target.

You may assume that each input would have exactly one solution, and you may not use the same element twice.

**Example：**

```
Given nums = [2, 7, 11, 15], target = 9,

Because nums[0] + nums[1] = 2 + 7 = 9,
return [0, 1].
```

题序 | 原题 | 官方题解
:------------: | :------------: | :-------------:
1 |[Two Sum](https://leetcode.com/problems/two-sum/) | [Solution](https://leetcode.com/problems/two-sum/solution/)


题解：

原题条件是在假设只有为一解，且同时一个值不能使用两次的前提下，给定一个整数数组，就下标对应数之和等于目标值结果集。

思路一：

暴力解题，就是对数组进行循环嵌套遍历，代码略。

思路二：

使用Map集和保存数据，将数组值作为 `key`，下标作为 `value`，在此基础上，对原数组进行遍历，每一次遍历判断目标值与当前数值差，即 `key` 是否存在，此时需要排除下标是当前值下标的结果，代码如下：

``` java
class Solution {
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
```