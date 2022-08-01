package com.gemini.nowcoder.dp;

import java.util.Scanner;

/**
 * HJ71 字符串通配符
 * https://www.nowcoder.com/practice/43072d50a6eb44d2a6c816a283b02036?tpId=37&tqId=21294
 *
 * @author 天何
 * @date 2022/7/26
 */
public class Wildcard {

    private static final int CASE_GAP = Math.abs('A' - 'a');

    private static boolean isLetterOrDigit(char ch) {
        return isLetter(ch) || isDigit(ch);
    }

    private static boolean isLetter(char ch) {
        return 'a' <= ch && ch <= 'z' || 'A' <= ch && ch <= 'Z';
    }

    private static boolean isDigit(char ch) {
        return '0' <= ch & ch <= '9';
    }

    private static boolean equalsIgnoreCase(char ch1, char ch2) {
        if (!isLetter(ch1) || !isLetter(ch2)) return false;
        if (ch1 == ch2) return true;
        return Math.abs(ch1 - ch2) == CASE_GAP;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String pattern = scanner.nextLine();
        String text = scanner.nextLine();
        scanner.close();

        int m = pattern.length();
        int n = text.length();

        // dp[i][j]: pattern[0..i)能否匹配text[0..j)
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;

        char p;
        for (int i = 1; i <= m; i++) {
            p = pattern.charAt(i - 1);
            dp[i][0] = (p == '*' && dp[i - 1][0]);
        }

        for (int j = 1; j <= n; j++) dp[0][j] = false;

        char ch;
        int k;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                p = pattern.charAt(i - 1);
                ch = text.charAt(j - 1);
                if (p == '*') {
                    // 星号匹配0个字符
                    dp[i][j] = dp[i - 1][j];
                    // 星号向前匹配一个或多个字符
                    k = j - 1;
                    while (k >= 0 && isLetterOrDigit(text.charAt(k))) {
                        if (dp[i - 1][k]) {
                            dp[i][j] = true;
                            break;
                        }
                        k--;
                    }
                } else if (p == '?') {
                    dp[i][j] = (isLetterOrDigit(ch) && dp[i - 1][j - 1]);
                } else if (isLetter(p)) {
                    dp[i][j] = (equalsIgnoreCase(p, ch) && dp[i - 1][j - 1]);
                } else {
                    dp[i][j] = (p == ch && dp[i - 1][j - 1]);
                }
            }
        }

        System.out.println(dp[m][n]);
    }
}
