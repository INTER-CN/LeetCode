package com.gemini.nowcoder.dp;

import java.util.Scanner;

/**
 * HJ103 Redraiment的走法
 * https://www.nowcoder.com/practice/24e6243b9f0446b081b1d6d32f2aa3aa?tpId=37&tqId=21326
 * 最长递增子序列，动态规划
 *
 * @author 天何
 * @date 2022/7/26
 */
public class LongestIncreasingSubarray {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) nums[i] = scanner.nextInt();
        scanner.close();

        // dp[i]: 以nums[i]结尾的LIS
        int[] dp = new int[n];
        int max = 1;
        dp[0] = 1;

        int len;
        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    len = dp[j] + 1;
                    if (len > dp[i]) dp[i] = len;
                }
            }
            if (dp[i] > max) max = dp[i];
        }

        System.out.println(max);
    }
}
