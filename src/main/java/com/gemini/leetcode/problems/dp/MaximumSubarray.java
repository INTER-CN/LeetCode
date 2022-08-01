package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/maximum-subarray/
 * 动态规划
 *
 * @author 天何
 * @date 2022/7/30
 */
public class MaximumSubarray {

    public int maxSubArray(int[] nums) {
        int n = nums.length;

        // dp[i]: 以nums[i]结尾的最优解
        int[] dp = new int[n];
        dp[0] = nums[0];

        int max = nums[0];
        for (int i = 1; i < n; i++) {
            dp[i] = (dp[i - 1] >= 0) ? nums[i] + dp[i - 1] : nums[i];
            if (dp[i] > max) max = dp[i];
        }

        return max;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(new MaximumSubarray().maxSubArray(nums));
    }
}
