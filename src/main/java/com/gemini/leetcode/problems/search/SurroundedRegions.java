package com.gemini.leetcode.problems.search;

import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/surrounded-regions/
 * 广度优先遍历
 *
 * @Author Gemini
 * 2022-08-21
 **/
public class SurroundedRegions {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public void solve(char[][] board) {
        int m = board.length;
        int n = board[0].length;

        // turn all 'O's and their neighbors into 'A'
        Queue<int[]> queue = new LinkedList<>();
        int[] position;
        int x, y;
        while (true) {
            position = findBorderPosition(board, m, n, 'O');
            if (position == null) break;
            board[position[0]][position[1]] = 'A';
            queue.add(position);
            while (!queue.isEmpty()) {
                int[] pos = queue.poll();
                for (int[] direction : DIRECTIONS) {
                    x = pos[0] + direction[0];
                    y = pos[1] + direction[1];
                    if (!inRange(m, n, x, y)) continue;
                    if (board[x][y] != 'O') continue;
                    board[x][y] = 'A';
                    queue.add(new int[]{x, y});
                }
            }
        }

        // turn all 'O's into 'X' and all 'A's into 'O'
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'O') board[i][j] = 'X';
                else if (board[i][j] == 'A') board[i][j] = 'O';
            }
        }
    }

    private int[] findBorderPosition(char[][] board, int m, int n, char target) {
        for (int j = 0; j < n; j++) if (board[0][j] == target) return new int[]{0, j};
        for (int i = 0; i < m; i++) if (board[i][n - 1] == target) return new int[]{i, n - 1};
        for (int j = n - 1; j > 0; j--) if (board[m - 1][j] == target) return new int[]{m - 1, j};
        for (int i = m - 1; i > 0; i--) if (board[i][0] == target) return new int[]{i, 0};
        return null;
    }

    private boolean inRange(int m, int n, int i, int j) {
        return 0 <= i && i < m && 0 <= j && j < n;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'X', 'O', 'O', 'X', 'O', 'X', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O'},
            {'O', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'X'},
            {'O', 'O', 'O', 'O', 'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'O', 'X', 'O', 'O'},
            {'O', 'X', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'X', 'X', 'O', 'O', 'O', 'X', 'O', 'O', 'X', 'O', 'O', 'X'},
            {'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O', 'O'}
        };
        new SurroundedRegions().solve(board);
        System.out.println(new Gson().toJson(board));
    }
}
