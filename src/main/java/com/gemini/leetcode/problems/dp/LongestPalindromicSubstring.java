package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/longest-palindromic-substring/
 * 动态规划，O(n^2)
 *
 * @author 天何
 * @date 2022/7/5
 */
public class LongestPalindromicSubstring {

    public String longestPalindrome(String s) {
        int n = s.length();

        // dp[i][j]: 子串s[i..j]是否回文
        boolean[][] dp = new boolean[n][n];

        int maxLength = 1;
        int maxStart = 0;
        for (int i = 0; i < n; i++) dp[i][i] = true;
        for (int i = 1; i < n; i++) dp[i][i - 1] = true;
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                dp[i][i + len - 1] = s.charAt(i) == s.charAt(i + len - 1) && dp[i + 1][i + len - 2];
                if (dp[i][i + len - 1] && len > maxLength) {
                    maxLength = len;
                    maxStart = i;
                }
            }
        }

        return s.substring(maxStart, maxStart + maxLength);
    }

    public static void main(String[] args) {
        String s = "babad";
        System.out.println(new LongestPalindromicSubstring().longestPalindrome(s));
    }
}
