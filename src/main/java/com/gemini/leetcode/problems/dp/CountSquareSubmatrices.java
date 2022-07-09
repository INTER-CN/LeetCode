package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/count-square-submatrices-with-all-ones/
 * 动态规划
 *
 * @author 天何
 * @date 2022/7/5
 */
public class CountSquareSubmatrices {

    public int countSquares(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;

        // dp[i][j]: 以matrix[i][j]为右下角的正方形个数
        int[][] dp = new int[m][n];

        int count = 0;

        for (int i = 0; i < m; i++) {
            dp[i][0] = matrix[i][0];
            count += dp[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = matrix[0][j];
            count += dp[0][j];
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                // 当前节点是0
                if (matrix[i][j] == 0) {
                    dp[i][j] = 0;
                    continue;
                }


                int subSide = dp[i - 1][j - 1];

                // 左上角是0
                if (subSide == 0) {
                    dp[i][j] = matrix[i][j];
                    count += dp[i][j];
                    continue;
                }

                int x = i - 1;
                while (i - x <= subSide && matrix[x][j] == 1) x--;
                if (x == i - 1) {
                    dp[i][j] = 1;
                } else {
                    int y = j - 1;
                    while (j - y <= subSide && matrix[i][y] == 1) y--;
                    dp[i][j] = Math.min(i - x, j - y);
                }

                count += dp[i][j];
            }
        }

        return count;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {0, 1, 1, 1},
            {1, 1, 1, 1},
            {1, 1, 1, 1}
        };
        System.out.println(new CountSquareSubmatrices().countSquares(matrix));
    }
}
