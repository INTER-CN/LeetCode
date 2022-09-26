package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/unique-paths-ii/
 * 二维数组动态规划
 * 水题
 *
 * @Author Gemini
 * 2022-08-30
 **/
public class UniquePaths2 {

    public int uniquePathsWithObstacles(int[][] grid) {
        if (grid[0][0] == 1) return 0;

        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];
        dp[0][0] = 1;
        for (int i = 1; i < m; i++) dp[i][0] = grid[i][0] == 1 ? 0 : dp[i - 1][0];
        for (int j = 1; j < n; j++) dp[0][j] = grid[0][j] == 1 ? 0 : dp[0][j - 1];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = grid[i][j] == 1 ? 0 : (dp[i - 1][j] + dp[i][j - 1]);
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 0},
            {0, 1, 0},
            {0, 0, 0}
        };
        System.out.println(new UniquePaths2().uniquePathsWithObstacles(grid));
    }
}
