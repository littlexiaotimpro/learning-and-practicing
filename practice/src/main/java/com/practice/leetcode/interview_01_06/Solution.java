package com.practice.leetcode.interview_01_06;

/**
 * @author XiaoSi
 * @className Solution
 * @description 压缩字符串
 * @date 2020/3/16
 */
public class Solution {
    /**
     * 思路一：累计并统计字符出现次数，拼接求结果
     */
    public String compressString(String S) {
        if (S == null || S.length() < 3) {
            // null || length < 3
            return S;
        } else {
            StringBuilder builder = new StringBuilder("");
            char c = S.charAt(0);
            builder.append(c);
            int count = 1;
            for (int i = 1; i < S.length(); i++) {
                if (c == S.charAt(i)) {
                    count += 1;
                } else {
                    builder.append(count);
                    // 出现新字符时重新赋值并统计新字符的个数
                    c = S.charAt(i);
                    count = 1;
                    builder.append(c);
                }
            }
            builder.append(count);
            String result = builder.toString();
            return result.length() >= S.length() ? S : result;
        }
    }
}
