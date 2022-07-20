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

    /**
     * 字节面试题
     */
    public int minSubArrayLen2(int target, int[] nums) {
        if (nums == null || nums.length == 0) return 0;

        int len = nums.length;
        int left = 0, right = -1;
        int sum = 0;
        int min = 0;
        while (right < len - 1) {
            // right后移
            while (right < len) {
                right++;
                if (right == len) break;
                sum += nums[right];
                if (sum >= target) break;
            }
            if (sum < target) break;

            // left后移
            while (left <= right) {
                sum -= nums[left];
                left++;
                if (sum < target) break;
            }

            int count = right - left + 2;
            if (min == 0 || count < min) min = count;
            if (min == 1) return min;
        }

        return min;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 4, 3, 9, 1};
        int target = 13;
        System.out.println(new MinimumSizeSubarraySum().minSubArrayLen2(target, nums));
    }
}
