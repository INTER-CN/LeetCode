package com.gemini.nowcoder.dp;

import java.util.Scanner;

/**
 * HJ75 公共子串计算
 * https://www.nowcoder.com/practice/98dc82c094e043ccb7e0570e5342dd1b?tpId=37&tqId=21298
 * 经典动态规划
 *
 * @author 天何
 * @date 2022/7/26
 */
public class LongestCommonSubstring2 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        scanner.close();

        int m = s1.length();
        int n = s2.length();

        // dp[i][j]: 以s1[i]和s2[j]结尾的LCS长度
        int[][] dp = new int[m][n];

        int max = 0;
        for (int i = 0; i < m; i++) {
            if (s1.charAt(i) == s2.charAt(0)) {
                dp[i][0] = 1;
                max = 1;
            }
        }
        for (int j = 1; j < n; j++) {
            if (s1.charAt(0) == s2.charAt(j)) {
                dp[0][j] = 1;
                max = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    if (dp[i][j] > max) max = dp[i][j];
                }
            }
        }

        System.out.println(max);
    }
}
