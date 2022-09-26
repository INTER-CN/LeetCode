package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/split-array-largest-sum/
 * 贪心 + 二分法
 * 神奇的算法
 *
 * @Author Gemini
 * 2022-08-22
 **/
public class SplitArrayLargestSum {

    public int splitArray(int[] nums, int m) {
        int n = nums.length;

        int sum = 0, max = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            if (nums[i] > max) max = nums[i];
        }

        if (m == 1) return sum;
        if (m == n) return max;

        int left = max, right = sum;
        int count;
        while (left < right) {
            if (left + 1 == right) {
                if (calculateSplitCount(nums, n, left) <= m) return left;
                else return right;
            }

            int mid = left + (right - left) / 2;
            count = calculateSplitCount(nums, n, mid);

            if (count > m) left = mid;
            else right = mid;
        }

        return left;
    }

    private int calculateSplitCount(int[] nums, int n, int limit) {
        int i = 0;
        int count = 1;
        int sum = 0;
        while (i < n) {
            sum += nums[i];
            if (sum > limit) {
                count++;
                sum = 0;
            } else {
                i++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {7, 2, 5, 10, 8};
        int m = 2;
        System.out.println(new SplitArrayLargestSum().splitArray(nums, m));
    }
}
