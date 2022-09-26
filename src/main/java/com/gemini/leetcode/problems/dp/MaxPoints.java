package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/maximum-number-of-points-with-cost/
 * 二维数组动态规划
 *
 * @Author Gemini
 * 2022-09-01
 **/
public class MaxPoints {

    public long maxPoints(int[][] points) {
        int m = points.length;
        int n = points[0].length;

        long[][] dp = new long[m][n];
        for (int j = 0; j < n; j++) dp[0][j] = points[0][j];

        long leftMax, rightMax;
        for (int i = 1; i < m; i++) {
            leftMax = dp[i - 1][0];
            dp[i][0] = leftMax + points[i][0];
            for (int j = 1; j < n; j++) {
                leftMax = Math.max(leftMax - 1, dp[i - 1][j]);
                dp[i][j] = leftMax + points[i][j];
            }
            rightMax = dp[i - 1][n - 1];
            if (rightMax + points[i][n - 1] > dp[i][n - 1]) dp[i][n - 1] = rightMax + points[i][n - 1];
            for (int j = n - 2; j >= 0; j--) {
                rightMax = Math.max(rightMax - 1, dp[i - 1][j]);
                if (rightMax + points[i][j] > dp[i][j]) dp[i][j] = rightMax + points[i][j];
            }
        }

        long max = 0;
        for (int j = 0; j < n; j++) if (dp[m - 1][j] > max) max = dp[m - 1][j];
        return max;
    }

    public static void main(String[] args) {
        int[][] points = {
            {1, 5},
            {2, 3},
            {4, 2}
        };
        System.out.println(new MaxPoints().maxPoints(points));
    }
}
