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

测试用例结果：

Runtime: 2 ms, faster than 98.88% of Java online submissions for Two Sum.

Memory Usage: 37.5 MB, less than 98.95% of Java online submissions for Two Sum.


结语
---
如果对代码有疑问，或者认为有更好的方法，欢迎指出

## 以下知识作为扩展
本文只介绍在算法中使用到的集合特性，并不会将整个Map属性及方法全部介绍，后续可能会在JDK源码学习系列文章中会对此进行准确的描述。

`Map` 官方定义是：

> public interface Map<K,V>  
> An object that maps keys to values. A map cannot contain duplicate keys; each key can map to at most one value.

它是一个接口对象，并以键值对的方式存储数据，一个键位对应一个值，且 `key` 为唯一值，不能重复。

