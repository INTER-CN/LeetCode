package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/find-peak-element/
 * 二分法
 *
 * @Author Gemini
 * 2022-08-20
 **/
public class FindPeakElement {

    public int findPeakElement(int[] nums) {
        return findPeakElement(nums, 0, nums.length - 1);
    }

    private int findPeakElement(int[] nums, int start, int end) {
        if (start > end) return -1;
        if (start == end) return start;
        if (nums[start] > nums[start + 1]) return start;
        if (nums[end] > nums[end - 1]) return end;

        int mid = start + (end - start) / 2;
        if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
            return mid;
        } else if (nums[mid] < nums[mid - 1]) {
            return findPeakElement(nums, start + 1, mid - 1);
        } else {
            return findPeakElement(nums, mid + 1, end - 1);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 5, 6, 4};
        System.out.println(new FindPeakElement().findPeakElement(nums));
    }
}
