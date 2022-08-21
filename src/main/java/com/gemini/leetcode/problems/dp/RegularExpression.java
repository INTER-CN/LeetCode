package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/regular-expression-matching/
 * 动态规划，正则匹配
 *
 * @Author Gemini
 * 2022-08-18
 **/
public class RegularExpression {

    public boolean isMatch(String s, String p) {
        // 对表达式进行处理，提取*
        int starCount = 0;
        for (char c : p.toCharArray()) {
            if (c == '*') starCount++;
        }

        int n = p.length() - starCount;
        char[] pattern = new char[n];
        boolean[] starFlag = new boolean[n];
        int index = 0;
        for (char c : p.toCharArray()) {
            if (c != '*') {
                pattern[index++] = c;
            } else {
                starFlag[index - 1] = true;
            }
        }

        int m = s.length();

        // dp[i][j]: s[0..i)和pattern[0..j)是否匹配
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        for (int i = 1; i <= m; i++) dp[i][0] = false;
        for (int j = 1; j <= n; j++) dp[0][j] = dp[0][j - 1] && starFlag[j - 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (starFlag[j - 1]) {
                    if (dp[i][j - 1]) {
                        dp[i][j] = true;
                    } else if (pattern[j - 1] == '.') {
                        for (int k = i - 1; k >= 0; k--) {
                            if (dp[k][j - 1]) {
                                dp[i][j] = true;
                                break;
                            }
                        }
                    } else {
                        for (int k = i - 1; k >= 0; k--) {
                            if (s.charAt(k) != pattern[j - 1]) break;
                            if (dp[k][j - 1]) {
                                dp[i][j] = true;
                                break;
                            }
                        }
                    }
                } else { // starFlag[j - 1] == false
                    dp[i][j] = dp[i - 1][j - 1] && (pattern[j - 1] == '.' || pattern[j - 1] == s.charAt(i - 1));
                }
            }
        }

        return dp[m][n];
    }

    public static void main(String[] args) {
        String s = "aa";
        String p = "aaa";
        System.out.println(new RegularExpression().isMatch(s, p));
    }
}
