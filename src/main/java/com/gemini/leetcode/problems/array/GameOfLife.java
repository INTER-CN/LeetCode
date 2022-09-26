package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/game-of-life/
 * 数组遍历
 * 略水
 *
 * @Author Gemini
 * 2022-08-31
 **/
public class GameOfLife {

    public void gameOfLife(int[][] board) {
        // 0(00): dead for now and for next
        // 1(01): live for now and dead for next
        // 2(10): dead for now and live for next
        // 3(11): live for now and for next

        int m = board.length;
        int n = board[0].length;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                nextState(board, i, j, m, n);
            }
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = board[i][j] >> 1;
            }
        }
    }

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};

    private boolean inRange(int i, int j, int m, int n) {
        return 0 <= i && i < m && 0 <= j && j < n;
    }

    private void nextState(int[][] board, int i, int j, int m, int n) {
        int liveCount = 0;
        int x, y;
        for (int[] direction : DIRECTIONS) {
            x = i + direction[0];
            y = j + direction[1];
            if (!inRange(x, y, m, n)) continue;
            if ((board[x][y] & 1) == 1) liveCount++;
        }
        if (board[i][j] == 1) {
            if (liveCount == 2 || liveCount == 3) board[i][j] |= 2;
        } else {
            if (liveCount == 3) board[i][j] |= 2;
        }
    }

    public static void main(String[] args) {
        int[][] board = {
            {0, 1, 0},
            {0, 0, 1},
            {1, 1, 1},
            {0, 0, 0}
        };
        new GameOfLife().gameOfLife(board);
        System.out.println(new Gson().toJson(board));
    }
}
