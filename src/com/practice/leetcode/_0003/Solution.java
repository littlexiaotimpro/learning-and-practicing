package com.practice.leetcode._0003;

import java.util.HashSet;
import java.util.Set;

/**
 * @ClassName Solution
 * @Description TODO
 * @Author XiaoSi
 * @Date 2019/8/1521:03
 */
public class Solution {

    /**
     * 求字符串的最长连续子串长度 - 方法一
     * 通过比较set集合与result的大小，对结果进行赋值
     * 当if中的结果存在时，更新result，清空set集合，执行下一循环
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        Set set = new HashSet();
        int result = 0;
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (set.contains(s.charAt(i))) {
                result = result <= set.size() ? set.size() : result;
                set.clear();
                i = count++;
                continue;
            } else {
                set.add(s.charAt(i));
                result = result <= set.size() ? set.size() : result;
            }
        }
        return result;
    }

    /**
     * 求字符串的最长连续子串长度 - 方法二
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstringTwo(String s) {

        return 0;
    }

}
