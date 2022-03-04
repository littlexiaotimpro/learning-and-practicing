package com.practice.algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *
 * @date 2022/3/4
 */
public class MinWindow {

    public static String minWindow(String s, String t) {
        int sl = s.length();
        int tl = t.length();
        Map<Character, Integer> needs = new HashMap<>(tl);
        Map<Character, Integer> win = new HashMap<>(sl);
        for (int i = 0; i < tl; i++) {
            needs.put(t.charAt(i), needs.getOrDefault(t.charAt(i), 0) + 1);
        }
        int left = 0;
        int right = 0;
        int v = 0;
        int start = 0;
        int len = Integer.MAX_VALUE;
        // 开始放大窗口
        while (right < sl) {
            char c = s.charAt(right);
            right++;
            if (needs.containsKey(c)) {
                win.put(c, win.getOrDefault(c, 0) + 1);
                if (win.get(c).compareTo(needs.get(c)) == 0) {
                    v++;
                }
            }
            // 如果找到符合条件的结果，开始收缩窗口
            while (v == needs.size()) {
                if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char d = s.charAt(left);
                left++;
                if (needs.containsKey(d)) {
                    if (win.get(d).compareTo(needs.get(d)) == 0) {
                        v--;
                    }
                    win.put(d, win.get(d) - 1);
                }
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(start, start+len);
    }

    public static void main(String[] args) {
        String s = "ADOBECODEBANC";
        String t = "ABC";
        String res = minWindow(s, t);
        System.out.println(res);
    }

}
