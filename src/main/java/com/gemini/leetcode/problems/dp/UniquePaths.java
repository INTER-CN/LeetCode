package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/unique-paths/
 * 动态规划水题
 *
 * @author 天何
 * @date 2022/7/17
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        if (m < 2 || n < 2) return 1;

        // dp[i][j]: 走到[i][j]的路径数
        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) dp[i][0] = 1;
        for (int j = 1; j < n; j++) dp[0][j] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new UniquePaths().uniquePaths(3, 7));
    }
}
