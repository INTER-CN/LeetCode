package com.gemini.leetcode.problems.easy;

/**
 * https://leetcode.cn/problems/valid-sudoku/
 * 校验数独，复杂度较高的水题
 *
 * @Author Gemini
 * 2022-09-06
 **/
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        // row
        for (int i = 0; i < 9; i++) {
            boolean[] flag = new boolean[9];
            for (int j = 0; j < 9; j++) {
                if (board[i][j] == '.') continue;
                if (flag[board[i][j] - '1']) return false;
                flag[board[i][j] - '1'] = true;
            }
        }

        // column
        for (int j = 0; j < 9; j++) {
            boolean[] flag = new boolean[9];
            for (int i = 0; i < 9; i++) {
                if (board[i][j] == '.') continue;
                if (flag[board[i][j] - '1']) return false;
                flag[board[i][j] - '1'] = true;
            }
        }

        // box
        int x, y;
        for (int a = 0; a < 9; a += 3) {
            for (int b = 0; b < 9; b += 3) {
                boolean[] flag = new boolean[9];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        x = a + i;
                        y = b + j;
                        if (board[x][y] == '.') continue;
                        if (flag[board[x][y] - '1']) return false;
                        flag[board[x][y] - '1'] = true;
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
        };
        System.out.println(new ValidSudoku().isValidSudoku(board));
    }
}
