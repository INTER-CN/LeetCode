package com.gemini.nowcoder.dp;

import java.util.Scanner;

/**
 * HJ65 查找两个字符串a,b中的最长公共子串
 * https://www.nowcoder.com/practice/181a1a71c7574266ad07f9739f791506?tpId=37&tqId=21288
 * 经典动态规划
 *
 * @author 天何
 * @date 2022/7/26
 */
public class LongestCommonSubstring {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.next();
        String s2 = scanner.next();
        scanner.close();

        int m = s1.length();
        int n = s2.length();

        // 确保输出在较短串中最先出现的子串
        if (m > n) {
            int x = m;
            m = n;
            n = x;
            String s = s1;
            s1 = s2;
            s2 = s;
        }

        // dp[i][j]: 以s1[i]和s2[j]结尾的LCS长度
        int[][] dp = new int[m][n];

        String result = "";
        int max = 0;

        if (s1.charAt(0) == s2.charAt(0)) {
            dp[0][0] = 1;
            max = 1;
            result = s1.substring(0, 1);
        }

        for (int i = 1; i < m; i++) {
            if (s1.charAt(i) == s2.charAt(0)) {
                dp[i][0] = 1;
                if (max == 0) {
                    max = 1;
                    result = s2.substring(0, 1);
                }
            }
        }

        for (int j = 1; j < n; j++) {
            if (s1.charAt(0) == s2.charAt(j)) {
                dp[0][j] = 1;
                if (max == 0) {
                    max = 1;
                    result = s1.substring(0, 1);
                }
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s1.charAt(i) == s2.charAt(j)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                    if (dp[i][j] > max) {
                        max = dp[i][j];
                        result = s1.substring(i + 1 - max, i + 1);
                    }
                }
            }
        }

        System.out.println(result);
    }
}
