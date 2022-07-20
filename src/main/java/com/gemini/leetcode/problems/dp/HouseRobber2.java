package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/house-robber-ii/
 * 动态规划，多条件，O(n)
 *
 * @author 天何
 * @date 2022/7/19
 */
public class HouseRobber2 {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;
        if (n == 1) return nums[0];
        if (n == 2) return Math.max(nums[0], nums[1]);

        // dp[0][0][i]: 首尾都不拿且不拿[i]的情况下，nums[0..i]的最优解
        // dp[0][1][i]: 首尾都不拿且拿[i]的情况下，nums[0..i]的最优解
        // dp[1][0][i]: 拿第一个且不拿[i]的情况下，nums[0..i]的最优解
        // dp[1][1][i]: 拿第一个且拿[i]的情况下，nums[0..i]的最优解
        // dp[2][0][i]: 拿最后一个且不拿[i]的情况下，nums[0..i]的最优解
        // dp[2][1][i]: 拿最后一个且拿[i]的情况下，nums[0..i]的最优解
        final int NONE = 0, FIRST = 1, LAST = 2, N = 0, Y = 1;
        int lastIndex = n - 1, preIndex;
        int[][][] dp = new int[3][2][n];

        dp[NONE][N][0] = 0;
        dp[NONE][Y][0] = 0;
        dp[FIRST][N][0] = 0;
        dp[FIRST][Y][0] = nums[0];
        dp[LAST][N][0] = 0;
        dp[LAST][Y][0] = 0;

        for (int i = 1; i < n; i++) {
            preIndex = i - 1;
            dp[NONE][N][i] = Math.max(dp[NONE][N][preIndex], dp[NONE][Y][preIndex]);
            dp[NONE][Y][i] = nums[i] + dp[NONE][N][preIndex];
            dp[FIRST][N][i] = Math.max(dp[FIRST][N][preIndex], dp[FIRST][Y][preIndex]);
            dp[FIRST][Y][i] = (i == 1 || i == lastIndex) ? 0 : nums[i] + dp[FIRST][N][preIndex];
            dp[LAST][N][i] = (i == lastIndex) ? 0 : Math.max(dp[LAST][N][preIndex], dp[LAST][Y][preIndex]);
            dp[LAST][Y][i] = (i == lastIndex - 1) ? 0 : nums[i] + dp[LAST][N][preIndex];
        }

        return getMax(
            dp[NONE][N][lastIndex], dp[NONE][Y][lastIndex],
            dp[FIRST][N][lastIndex], dp[FIRST][Y][lastIndex],
            dp[LAST][N][lastIndex], dp[LAST][Y][lastIndex]
        );
    }

    private static int getMax(Integer... values) {
        int max = Integer.MIN_VALUE;
        for (Integer value : values) {
            if (value > max) max = value;
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1};
        System.out.println(new HouseRobber2().rob(nums));
    }
}
