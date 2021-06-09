package com.practice.algorithm.backtracking;

import java.util.Arrays;

/**
 * 回溯法解N皇后问题（同行、同列、同对角线有且仅有一位皇后）
 * 求所有可能的结果
 */
public class NQueen {

    private static final NQueen nQueen = new NQueen();

    /**
     * 可以选择行或列作为遍历标准
     * 如下：列
     *
     * @param queens 结果集合
     * @param col    当前列
     */
    private void backtrack(char[][] queens, int col) {
        if (col >= queens.length) {
            System.out.println("************************");
            for (char[] queen : queens) {
                System.out.println(Arrays.toString(queen));
            }
            return;
        }
        for (int row = 0; row < queens.length; row++) {
            // 当前位置是否可以放置 Queen
            if (!isValid(queens, row, col)) continue;
            // 放置 Queen
            queens[row][col] = 'Q';
            // 继续下一个
            backtrack(queens, col + 1);
            // 撤销当前选择（回溯）
            queens[row][col] = '.';
        }
    }

    /**
     * 当前放置位置是否合理
     *
     * @param queens 结果集合
     * @param row    所在行
     * @param col    所在列
     * @return 【false:位置不符】
     */
    private boolean isValid(char[][] queens, int row, int col) {
        int n = queens.length;
        // 行
        for (int i = 0; i < col; i++) {
            if (queens[row][i] == 'Q') {
                return false;
            }
        }
        // 左上
        int r = row - 1;
        int c = col - 1;
        while (r >= 0 && c >= 0) {
            if (queens[r][c] == 'Q') {
                return false;
            }
            r--;
            c--;
        }
        // 左下
        r = row + 1;
        c = col - 1;
        while (r < n && c >= 0) {
            if (queens[r][c] == 'Q') {
                return false;
            }
            r++;
            c--;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 8;
        char[][] queens = new char[n][n];
        for (char[] queen : queens) {
            Arrays.fill(queen, '.');
        }
        nQueen.backtrack(queens, 0);
    }
}
