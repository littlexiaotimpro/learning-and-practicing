package com.practice.leetcode._1071;

/**
 * @author XiaoSi
 * @className Solution
 * @description 字符串的最大公因子
 * @date 2020/3/12
 */
public class Solution {

    /**
     * 思路一
     * 通过计算两个字符串长度的最大公因子
     * 截取子串，然后比较两个资源字符串能否被子串完全替换
     *
     * @param str1 资源字符串：1
     * @param str2 资源字符串：2
     * @return 结果
     */
    public String gcdOfStrings(String str1, String str2) {
        int s1 = str1.length();
        int s2 = str2.length();
        int result = 0;
        for (int i = 1; i <= Math.min(s1, s2); i++) {
            if (s1 % i == 0 && s2 % i == 0) {
                result = i;
            }
        }
        String answer = str1.substring(0, result);
//        str1 = str1.replaceAll(answer, "");
//        str2 = str2.replaceAll(answer, "");
//        return "".equals(str1) && "".equals(str2) ? answer : "";
        int lastIndexOf1 = str1.lastIndexOf(answer);
        int lastIndexOf2 = str2.lastIndexOf(answer);
        return (s1 + s2 - 2 * result) == (lastIndexOf1 + lastIndexOf2) ? answer : "";
    }


    /**
     * 思路二
     * 数学思想中求最大公约数的方法：辗转相除法
     *
     * @param str1 资源字符串：1
     * @param str2 资源字符串：2
     * @return 结果
     */
    public String gcdOfStrings_best(String str1, String str2) {
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        // 辗转相除法求gcd。
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.gcdOfStrings("ABCABC", "ABC"));
        System.out.println(solution.gcdOfStrings("AAD", "AA"));
        System.out.println(solution.gcdOfStrings("AAAAAA", "AAAA"));
        System.out.println(solution.gcdOfStrings_best("ABCABC", "ABC"));
        System.out.println(solution.gcdOfStrings_best("AAD", "AA"));
        System.out.println(solution.gcdOfStrings_best("AAAAAA", "AAAA"));
    }
}
