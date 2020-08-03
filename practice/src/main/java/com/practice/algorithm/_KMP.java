package com.practice.algorithm;

import java.util.Arrays;

public class _KMP {

    private static void patternPrefix(CharSequence cs, int[] prefix, int n) {
        prefix[0] = 0;
        int cursor = 0;
        int i = 1;
        while (i < n) {
            if (cs.charAt(i) == cs.charAt(cursor)) {
                prefix[i] = ++cursor;
                i++;
            } else {
                if (cursor > 0) {
                    cursor = prefix[cursor - 1];
                } else {
                    prefix[i] = cursor;
                    i++;
                }
            }
        }
        System.out.println("最大公共前后缀：" + Arrays.toString(prefix));
        next(prefix, n);
    }

    private static void next(int[] prefix, int n) {
        for (int i = n - 1; i >= 1; i--) {
            prefix[i] = prefix[i - 1];
        }
        prefix[0] = -1;
        System.out.println("失配对应索引位：" + Arrays.toString(prefix));
    }

    private static int templateFirstMatches(CharSequence tcs, CharSequence pcs, int[] prefix) {
        int tLength = tcs.length();
        int j = 0;
        int i = 0;
        while (i < tLength) {
            if (tcs.charAt(i) == pcs.charAt(j)) {
                i++;
                j++;
                if (j == pcs.length()) {
                    return i - j;
                }
            } else {
                j = prefix[j];
                if (j == -1) {
                    i++;
                    j++;
                }
            }
        }
        return -1;
    }

    private static void templateAllMatches(CharSequence tcs, CharSequence pcs, int[] prefix) {
        int tLength = tcs.length();
        int j = 0;
        int i = 0;
        StringBuilder res = new StringBuilder();
        while (i < tLength) {
            if (tcs.charAt(i) == pcs.charAt(j)) {
                i++;
                j++;
                if (j == pcs.length()) {
                    res.append(i - j).append(" ");
                    i--;
                    j = prefix[j - 1];
                }
            } else {
                j = prefix[j];
                if (j == -1) {
                    i++;
                    j++;
                }
            }
        }
        System.out.println(res.length() <= 0 ? "在主串中未找到模式串！" : "模式串出现的位置：" + res.toString());
    }

    public static void main(String[] args) {

        String pattern = "ababab";
        int length = pattern.length();
        int[] prefix = new int[length];
        patternPrefix(pattern, prefix, length);

        String template = "abababaaaaabababvsfacav";
        System.out.println("-----------------首个匹配项-------------------");
        int firstIndex = templateFirstMatches(template, pattern, prefix);
        if (firstIndex >= 0) {
            System.out.println("模式串首次出现于：" + firstIndex);
        } else {
            System.out.println("在主串中未找到模式串！");
        }

        System.out.println("-----------------所有匹配项-------------------");
        templateAllMatches(template, pattern, prefix);
    }
}

