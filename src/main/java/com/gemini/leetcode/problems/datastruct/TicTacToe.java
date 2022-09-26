package com.gemini.leetcode.problems.datastruct;

/**
 * https://leetcode.cn/problems/design-tic-tac-toe/
 * 一维搜索
 * 水题
 *
 * @Author Gemini
 * 2022-08-24
 **/
public class TicTacToe {

    private char[][] board;
    private int n;

    public TicTacToe(int n) {
        this.n = n;
        this.board = new char[n][n];
    }

    public int move(int row, int col, int player) {
        char symbol = getSymbol(player);
        board[row][col] = symbol;

        // horizontal
        boolean wins = true;
        for (int j = 0; j < n; j++) {
            if (board[row][j] != symbol) {
                wins = false;
                break;
            }
        }
        if (wins) return player;

        // vertical
        wins = true;
        for (int i = 0; i < n; i++) {
            if (board[i][col] != symbol) {
                wins = false;
                break;
            }
        }
        if (wins) return player;

        // diagonal
        if (row == col) {
            wins = true;
            for (int i = 0; i < n; i++) {
                if (board[i][i] != symbol) {
                    wins = false;
                    break;
                }
            }
            if (wins) return player;
        }
        if (row + col == n - 1) {
            wins = true;
            for (int i = 0; i < n; i++) {
                if (board[i][n - 1 - i] != symbol) {
                    wins = false;
                    break;
                }
            }
            if (wins) return player;
        }

        return 0;
    }

    private char getSymbol(int player) {
        switch (player) {
            case 1:
                return 'X';
            case 2:
                return 'O';
            default:
                return 0;
        }
    }
}
