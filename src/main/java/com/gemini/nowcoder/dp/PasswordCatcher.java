package com.gemini.nowcoder.dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * HJ32 密码截取
 * https://www.nowcoder.com/practice/3cd4621963e8454594f00199f4536bb1?tpId=37&tqId=21255
 * 回文字符串，动态规划
 *
 * @author 天何
 * @date 2022/7/12
 */
public class PasswordCatcher {

    public static void main(String[] args) throws IOException {
        // input
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        br.close();

        // dp[i][j]: s[i..j]是否回文
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int max = 1;

        for (int i = 0; i < n; i++) dp[i][i] = true;
        for (int i = 1; i < n; i++) dp[i][i - 1] = true;

        int j;
        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len <= n; i++) {
                j = i + len - 1;
                dp[i][j] = s.charAt(i) == s.charAt(j) && dp[i + 1][j - 1];
                if (dp[i][j] && len > max) max = len;
            }
        }

        System.out.println(max);
    }
}
