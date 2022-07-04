package com.gemini.leetcode.problems;

/**
 * https://leetcode.cn/problems/maximal-square/
 * 动态规划
 *
 * @author 天何
 * @date 2022/6/30
 */
public class MaximalSquare {

    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }

        int m = matrix.length;

        if (matrix[0] == null || matrix[0].length == 0) {
            return 0;
        }

        int n = matrix[0].length;

        // 以每个点为右下角的最大正方形边长
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i++) dp[i][0] = matrix[i][0] - '0';
        for (int i = 0; i < n; i++) dp[0][i] = matrix[0][i] - '0';

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (matrix[i][j] == '0') {
                    dp[i][j] = 0;
                } else { // matrix[i][j] == '1'
                    int subSide = dp[i - 1][j - 1];
                    int subHeight = 1;
                    for (int k = i - 1; k >= i - subSide; k--) {
                        if (matrix[k][j] == '0') {
                            break;
                        } else {
                            subHeight++;
                        }
                    }
                    if (subHeight == 1) {
                        dp[i][j] = 1;
                        continue;
                    }
                    int subWidth = 1;
                    for (int k = j - 1; k >= j - subSide; k--) {
                        if (matrix[i][k] == '0') {
                            break;
                        } else {
                            subWidth++;
                        }
                    }
                    if (subWidth == 1) {
                        dp[i][j] = 1;
                        continue;
                    }
                    dp[i][j] = Math.min(subHeight, subWidth);
                }
            }
        }

        int maxSide = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (dp[i][j] > maxSide) maxSide = dp[i][j];
            }
        }

        return maxSide * maxSide;
    }

    public static void main(String[] args) {
        char[][] matrix = {
            {'0', '0', '0', '1'},
            {'1', '1', '0', '1'},
            {'1', '1', '1', '1'},
            {'0', '1', '1', '1'},
            {'0', '1', '1', '1'}
        };
        int result = new MaximalSquare().maximalSquare(matrix);
        System.out.println(result);
    }
}
