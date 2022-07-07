package com.gemini.leetcode.problems;

/**
 * https://leetcode.cn/problems/palindromic-substrings/
 * 动态规划，O(n^2)
 *
 * @author 天何
 * @date 2022/7/5
 */
public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        int n = s.length();

        // dp[i][j]: 子串s[i..j]是否回文
        boolean[][] dp = new boolean[n][n];

        for (int i = 0; i < n; i++) dp[i][i] = true;
        for (int i = 1; i < n; i++) dp[i][i - 1] = true;

        int palindromeCount = n;

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                dp[i][i + len - 1] = s.charAt(i) == s.charAt(i + len - 1) && dp[i + 1][i + len - 2];
                if (dp[i][i + len - 1]) {
                    palindromeCount++;
                }
            }
        }

        return palindromeCount;
    }

    public static void main(String[] args) {
        String s = "aaa";
        System.out.println(new PalindromicSubstrings().countSubstrings(s));
    }
}
