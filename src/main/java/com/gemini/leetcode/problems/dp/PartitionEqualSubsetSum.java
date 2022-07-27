package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/partition-equal-subset-sum/
 * 动态规划
 *
 * @author 天何
 * @date 2022/7/25
 */
public class PartitionEqualSubsetSum {

    public boolean canPartition(int[] nums) {
        if (nums == null || nums.length < 2) return false;

        int sum = 0;
        for (int num : nums) sum += num;

        if (sum % 2 == 1) return false;

        int m = sum / 2;

        // dp[i]: 是否存在和为 i 的子数组
        boolean[] dp = new boolean[m + 1];

        dp[0] = true;

        for (int num : nums) {
            for (int i = m; i > 0; i--) {
                if (i >= num) {
                    dp[i] = dp[i] || dp[i - num];
                }
            }
        }

        return dp[m];
    }

    public static void main(String[] args) {
        int[] nums = {1, 5, 5, 11};
        System.out.println(new PartitionEqualSubsetSum().canPartition(nums));
    }
}
