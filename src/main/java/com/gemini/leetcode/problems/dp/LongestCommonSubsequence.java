package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/longest-common-subsequence/
 * 动态规划
 *
 * @author 天何
 * @date 2022/7/9
 */
public class LongestCommonSubsequence {

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();

        // dp[i][j]: text1[0..i]与text2[0..j]的最长公共子序列
        int[][] dp = new int[m][n];

        dp[0][0] = text1.charAt(0) == text2.charAt(0) ? 1 : 0;

        for (int i = 1; i < m; i++) dp[i][0] = text1.charAt(i) == text2.charAt(0) ? 1 : dp[i - 1][0];
        for (int j = 1; j < n; j++) dp[0][j] = text1.charAt(0) == text2.charAt(j) ? 1 : dp[0][j - 1];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                if (text1.charAt(i) == text2.charAt(j)) dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
            }
        }

        return dp[m - 1][n - 1];
    }

    public static void main(String[] args) {
        System.out.println(new LongestCommonSubsequence().longestCommonSubsequence("abcde", "ace"));
    }
}
