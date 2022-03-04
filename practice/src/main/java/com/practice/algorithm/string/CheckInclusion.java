package com.practice.algorithm.string;

import java.util.HashMap;
import java.util.Map;

/**
 * 给你两个字符串s1和s2 ，写一个函数来判断 s2 是否包含 s1的排列。如果是，返回 true ；否则，返回 false 。
 * 换句话说，s1 的排列之一是 s2 的 子串 。
 *
 * @date 2022/3/4
 */
public class CheckInclusion {

    public static void main(String[] args) {
        String s = "eidbaooo";
        String t = "ab";
        boolean res = checkInclusion(s, t);
        System.out.println(res);
    }

    private static boolean checkInclusion(String s, String t) {
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
        while (right < sl) {
            char c = s.charAt(right);
            right++;
            if (needs.containsKey(c)) {
                win.put(c, win.getOrDefault(c, 0) + 1);
                if (win.get(c).compareTo(needs.get(c)) == 0) {
                    v++;
                }
            }
            // 判断左侧窗⼝是否要收缩
            while (right - left >= tl) {
                // 在这⾥判断是否找到了合法的⼦串
                if (v == needs.size())
                    return true;
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
        return false;
    }
}
