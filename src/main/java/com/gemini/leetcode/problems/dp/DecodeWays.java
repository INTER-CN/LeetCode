package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/decode-ways/
 * 动态规划
 *
 * @Author Gemini
 * 2022-08-30
 **/
public class DecodeWays {

    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;
        if (s.length() == 1) return 1;

        int n = s.length();
        int[] dp = new int[n];
        dp[0] = 1;

        if (s.charAt(1) == '0') {
            dp[1] = (s.charAt(0) == '1' || s.charAt(0) == '2') ? 1 : 0;
        } else {
            dp[1] = (s.charAt(0) == '1' || s.charAt(0) == '2' && s.charAt(1) < '7') ? 2 : 1;
        }


        boolean allows2Digit;
        for (int i = 2; i < n; i++) {
            if (s.charAt(i) == '0') {
                if (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2') dp[i] = dp[i - 2];
                else dp[i] = 0;
            } else {
                allows2Digit = (s.charAt(i - 1) == '1' || s.charAt(i - 1) == '2' && s.charAt(i) < '7');
                dp[i] = allows2Digit ? (dp[i - 2] + dp[i - 1]) : dp[i - 1];
            }
        }

        return dp[n - 1];
    }

    public static void main(String[] args) {
        String s = "226";
        System.out.println(new DecodeWays().numDecodings(s));
    }
}
