package com.gemini.leetcode.problems.dp;

import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/triangle/
 * 动态规划
 *
 * @Author Gemini
 * 2022-08-31
 **/
public class Triangle {

    public int minimumTotal(List<List<Integer>> triangle) {
        int n = triangle.size();

        int[] dp = new int[n];
        dp[0] = triangle.get(0).get(0);

        for (int k = 1; k < n; k++) {
            List<Integer> list = triangle.get(k);
            dp[k] = dp[k - 1] + list.get(k);
            for (int i = k - 1; i > 0; i--) dp[i] = list.get(i) + Math.min(dp[i], dp[i - 1]);
            dp[0] += list.get(0);
        }

        int result = dp[0];
        for (int i = 1; i < n; i++) if (dp[i] < result) result = dp[i];

        return result;
    }

    public static void main(String[] args) {
        List<List<Integer>> triangle = Arrays.asList(
            Arrays.asList(2),
            Arrays.asList(3, 4),
            Arrays.asList(6, 5, 7),
            Arrays.asList(4, 1, 8, 3)
        );
        System.out.println(new Triangle().minimumTotal(triangle));
    }
}
