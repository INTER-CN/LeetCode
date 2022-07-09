package com.gemini.leetcode.problems;

/**
 * https://leetcode.cn/problems/maximum-product-subarray/
 * 动态规划
 *
 * @author 天何
 * @date 2022/7/7
 */
public class MaximumProductSubarray {

    public int maxProduct(int[] nums) {
        int n = nums.length;

        // min[i]: 以nums[i]结尾的最小乘积
        // max[i]: 以nums[i]结尾的最大乘积
        int[] min = new int[n];
        int[] max = new int[n];
        min[0] = nums[0];
        max[0] = nums[0];
        int maxProduct = max[0];

        for (int i = 1; i < n; i++) {
            min[i] = Math.min(nums[i], Math.min(min[i - 1] * nums[i], max[i - 1] * nums[i]));
            max[i] = Math.max(nums[i], Math.max(min[i - 1] * nums[i], max[i - 1] * nums[i]));
            if (max[i] > maxProduct) maxProduct = max[i];
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 3, -2, 4};
        System.out.println(new MaximumProductSubarray().maxProduct(nums));
    }
}
