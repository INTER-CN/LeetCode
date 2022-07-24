package com.gemini.nowcoder.dp;

import java.util.Scanner;

/**
 * HJ52 计算字符串的编辑距离
 * https://www.nowcoder.com/practice/3959837097c7413a961a135d7104c314?tpId=37&tqId=21275
 * 动态规划
 *
 * @author 天何
 * @date 2022/7/21
 */
public class EditDistance {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        String s2 = scanner.next();
        scanner.close();

        int m = s1.length();
        int n = s2.length();

        // dp[i][j]: s1[0..i]和s2[0..j]的编辑距离
        int[][] dp = new int[m][n];

        dp[0][0] = s1.charAt(0) == s2.charAt(0) ? 0 : 1;
        for (int i = 1; i < m; i++) dp[i][0] = s1.charAt(i) == s2.charAt(0) ? i : 1 + dp[i - 1][0];
        for (int j = 1; j < n; j++) dp[0][j] = s1.charAt(0) == s2.charAt(j) ? j : 1 + dp[0][j - 1];

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], 1 + Math.min(dp[i - 1][j], dp[i][j - 1]));
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }

        System.out.println(dp[m - 1][n - 1]);
    }
}
