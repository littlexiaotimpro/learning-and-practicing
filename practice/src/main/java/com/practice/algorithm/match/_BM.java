package com.practice.algorithm.match;

/**
 * Boyer-Moore 字符串匹配算法
 */
public class _BM {

    private static final int SIZE = 2 << 7;

    /**
     * 坏字符规则
     * 查询表长度默认设置为 256 = 2^8
     * 采用字符的 ASCII 码十进制值作为索引
     */
    private static void badCharacter(CharSequence cs, int[] bc) {
        for (int i = 0; i < _BM.SIZE; i++) {
            bc[i] = -1;
        }
        for (int i = 0; i < cs.length(); i++) {
            bc[cs.charAt(i)] = i;
        }
    }

    /**
     * 好后缀规则
     * 查询表长度默认为模式串长度
     */
    private static void goodSuffix(CharSequence cs, int[] gs) {

    }

    /**
     * 字符失配时，返回模式串移动的位数
     */
    private static int misMatchSkip(char mc, int j, int[] bc, int[] gs) {
        return j - bc[mc];
    }

    public static void main(String[] args) {
        String pattern = "abababbasre";
        int[] bc = new int[_BM.SIZE];
        int[] gs = new int[pattern.length()];
        badCharacter(pattern, bc);
        goodSuffix(pattern, gs);

        int skip = misMatchSkip('a',0, bc, gs);
        System.out.println(String.format("%c字符失配时，模式串的移动位数：[坏字符规则=%d, 好后缀规则=%d]", 'a', skip, 0));
        skip = misMatchSkip('v', 8,bc, gs);
        System.out.println(String.format("%c字符失配时，模式串的移动位数：[坏字符规则=%d, 好后缀规则=%d]", 'v', skip, 0));
    }
}
