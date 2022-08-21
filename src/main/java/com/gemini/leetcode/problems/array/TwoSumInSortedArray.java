package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
 * 二分法
 *
 * @Author Gemini
 * 2022-08-21
 **/
public class TwoSumInSortedArray {

    public int[] twoSum(int[] nums, int target) {
        int left = 0, right = nums.length - 1, sum, binSum;
        while (true) {
            sum = nums[left] + nums[right];
            if (sum == target) return new int[]{left + 1, right + 1};
            int mid = left + (right - left) / 2;
            if (sum > target) {
                binSum = nums[left] + nums[mid];
                if (binSum == target) return new int[]{left + 1, mid + 1};
                if (binSum > target) {
                    right = mid - 1;
                } else {
                    right--;
                }
            } else {
                // sum < target
                binSum = nums[mid] + nums[right];
                if (binSum == target) return new int[]{mid + 1, right + 1};
                if (binSum < target) {
                    left = mid + 1;
                } else {
                    left++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        int[] result = new TwoSumInSortedArray().twoSum(nums, target);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
