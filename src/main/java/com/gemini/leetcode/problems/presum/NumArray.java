package com.gemini.leetcode.problems.presum;

/**
 * https://leetcode.cn/problems/range-sum-query-immutable/
 * 前缀和
 *
 * @Author Gemini
 * 2022-08-27
 **/
public class NumArray {

    private int[] preSum;

    public NumArray(int[] nums) {
        int n = nums.length;
        preSum = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            preSum[i] = sum;
        }
    }

    public int sumRange(int left, int right) {
        if (left == 0) return preSum[right];
        else return preSum[right] - preSum[left - 1];
    }

}
