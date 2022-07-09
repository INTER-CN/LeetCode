package com.gemini.leetcode.problems.logic;

/**
 * https://leetcode.cn/problems/minimum-size-subarray-sum/
 * 线性扫描
 *
 * @author 天何
 * @date 2022/7/7
 */
public class MinimumSizeSubarraySum {

    public int minSubArrayLen(int target, int[] nums) {
        if (nums[0] >= target) return 1;

        int sum = nums[0], left = 0, right = 0;
        int n = nums.length;
        int minSize = Integer.MAX_VALUE;
        while (left < n && right < n) {
            // 右指针右移直到满足条件
            while (right + 1 < n && sum < target) sum += nums[++right];
            if (sum < target) {
                return minSize == Integer.MAX_VALUE ? 0 : minSize;
            }
            // 左指针右移直到满足条件的最小长度
            while (left <= right && sum - nums[left] >= target) sum -= nums[left++];
            minSize = Math.min(minSize, right - left + 1);
            if (minSize == 1) return 1;
            sum -= nums[left++];
        }

        return minSize;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 2, 4, 3};
        int target = 13;
        System.out.println(new MinimumSizeSubarraySum().minSubArrayLen(target, nums));
    }
}
