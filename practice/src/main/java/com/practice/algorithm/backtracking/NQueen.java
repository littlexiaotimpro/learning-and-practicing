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
    private void backtrack_all(char[][] queens, int col) {
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
            backtrack_all(queens, col + 1);
            // 撤销当前选择（回溯）
            queens[row][col] = '.';
        }
    }

    /**
     * 可以选择行或列作为遍历标准
     * 如下：行
     *
     * @param queens 结果集合
     * @param row    当前行
     * @return 【true:求得任意解】
     */
    private boolean backtrack_one(char[][] queens, int row) {
        if (row >= queens.length) {
            for (char[] queen : queens) {
                System.out.println(Arrays.toString(queen));
            }
            return true;
        }
        for (int col = 0; col < queens.length; col++) {
            // 当前位置是否可以放置 Queen
            if (!isValid(queens, row, col)) continue;
            // 放置 Queen
            queens[row][col] = 'Q';
            // 继续下一个
            boolean r = backtrack_one(queens, row + 1);
            if (r) return true;
            // 撤销当前选择（回溯）
            queens[row][col] = '.';
        }
        return false;
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
        // 列
        for (int i = 0; i < col; i++) {
            if (queens[row][i] == 'Q') {
                return false;
            }
        }
        // 行
        for (int i = 0; i < row; i++) {
            if (queens[i][col] == 'Q') {
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
        // 右上
        r = row - 1;
        c = col + 1;
        while (r >= 0 && c < n) {
            if (queens[r][c] == 'Q') {
                return false;
            }
            r--;
            c++;
        }
        return true;
    }

    public static void main(String[] args) {
        int n = 8;
        char[][] queens = new char[n][n];
        for (char[] queen : queens) {
            Arrays.fill(queen, '.');
        }
        // 所有解
//         nQueen.backtrack_all(queens, 0);
        // 任意解
        nQueen.backtrack_one(queens, 0);
    }
}
