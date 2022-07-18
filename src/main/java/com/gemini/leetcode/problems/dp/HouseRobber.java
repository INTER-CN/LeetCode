package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/house-robber/
 * 动态规划
 *
 * @author 天何
 * @date 2022/7/18
 */
public class HouseRobber {

    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int n = nums.length;

        // dp0[i]: 不偷[i]的情况下，nums[0..i]的最优解
        // dp1[i]: 偷[i]的情况下，nums[0..i]的最优解
        int[] dp0 = new int[n];
        int[] dp1 = new int[n];

        dp0[0] = 0;
        dp1[0] = nums[0];

        for (int i = 1; i < n; i++) {
            dp0[i] = Math.max(dp0[i - 1], dp1[i - 1]);
            dp1[i] = nums[i] + dp0[i - 1];
        }

        return Math.max(dp0[n - 1], dp1[n - 1]);
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println(new HouseRobber().rob(nums));
    }
}
