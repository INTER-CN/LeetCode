package com.gemini.leetcode.problems.logic;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/candy-crush/
 * 模拟（并非四邻域搜索）
 *
 * @Author Gemini
 * 2022-08-22
 **/
public class CandyCrush {

    public int[][] candyCrush(int[][] board) {
        int m = board.length;
        int n = board[0].length;

        while (true) {
            // candy crush for a snapshot
            boolean crushed = crush(board, m, n);
            if (!crushed) break;

            // candy drop
            drop(board, m, n);
        }

        return board;
    }

    private static final int CRUSH_NUM = 3;

    private boolean crush(int[][] board, int m, int n) {
        boolean[][] flags = new boolean[m][n];
        int i, j, k;

        // horizontal
        for (i = 0; i < m; i++) {
            j = 0;
            while (j + 2 < n) {
                while (j + 2 < n && board[i][j] != board[i][j + 1]) j++;
                if (j + 2 == n) break;
                k = j + 2;
                while (k < n && board[i][k] == board[i][j]) k++;
                if (board[i][j] > 0 && k - j >= CRUSH_NUM) {
                    for (int index = j; index < k; index++) flags[i][index] = true;
                }
                j = k;
            }
        }

        // vertical
        for (j = 0; j < n; j++) {
            i = 0;
            while (i + 2 < m) {
                while (i + 2 < m && board[i][j] != board[i + 1][j]) i++;
                if (i + 2 == m) break;
                k = i + 2;
                while (k < m && board[k][j] == board[i][j]) k++;
                if (board[i][j] > 0 && k - i >= CRUSH_NUM) {
                    for (int index = i; index < k; index++) flags[index][j] = true;
                }
                i = k;
            }
        }

        boolean crushed = false;
        for (i = 0; i < m; i++) {
            for (j = 0; j < n; j++) {
                if (board[i][j] > 0 && flags[i][j]) {
                    board[i][j] = 0;
                    crushed = true;
                }
            }
        }

        return crushed;
    }

    private void drop(int[][] board, int m, int n) {
        int i, k;
        for (int j = 0; j < n; j++) {
            k = m - 1;
            for (i = m - 1; i >= 0; i--) {
                if (board[i][j] > 0) board[k--][j] = board[i][j];
            }
            while (k >= 0) board[k--][j] = 0;
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {110, 5, 112, 113, 114},
            {210, 211, 5, 213, 214},
            {310, 311, 3, 313, 314},
            {410, 411, 412, 5, 414},
            {5, 1, 512, 3, 3},
            {610, 4, 1, 613, 614},
            {710, 1, 2, 713, 714},
            {810, 1, 2, 1, 1},
            {1, 1, 2, 2, 2},
            {4, 1, 4, 4, 1014}
        };
        int[][] result = new CandyCrush().candyCrush(board);
        System.out.println(new Gson().toJson(result));
    }
}
