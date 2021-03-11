package com.practice.leetcode._0003;

import java.util.HashSet;
import java.util.Set;

/**
 * 求字符串的最长连续无重复子串长度
 */
public class Solution {

    /**
     * 方法一
     * 通过比较 set 集合与 result 的大小，对结果进行赋值
     * 当 if 中的结果存在时，更新 result，清空 set 集合，执行下一循环
     */
    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int result = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                result = Math.max(result, set.size());
                set.clear();
                i = count++;
            } else {
                set.add(s.charAt(i));
                result = Math.max(result, set.size());
            }
        }
        return result;
    }

    /**
     * 方法二 - 滑动窗口
     * s = "abcabcbb"
     * a -> ab -> abc，next a
     * 移除字符 a，添加新的字符，得到 bca
     * ...
     */
    public int lengthOfLongestSubstringTwo(String s) {
        Set<Character> set = new HashSet<>();
        int result = 0;
        int cursor = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            if (i != 0) {
                set.remove(s.charAt(i - 1));
            }
            while (cursor < n && !set.contains(s.charAt(cursor))) {
                set.add(s.charAt(cursor));
                cursor++;
            }
            result = Math.max(result, set.size());
        }
        return result;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "abcabcdbb";
        int res = solution.lengthOfLongestSubstringTwo(s);
        System.out.println(res);
    }

}
