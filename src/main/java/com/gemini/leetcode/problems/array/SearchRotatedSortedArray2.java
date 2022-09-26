package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array-ii/
 * 二分法
 *
 * @Author Gemini
 * 2022-09-22
 **/
public class SearchRotatedSortedArray2 {

    public boolean search(int[] nums, int target) {
        return search(nums, 0, nums.length - 1, target);
    }

    private boolean search(int[] nums, int start, int end, int target) {
        if (start > end) return false;
        if (start == end) return nums[start] == target;
        if (start + 1 == end) return nums[start] == target || nums[end] == target;

        if (nums[start] == target || nums[end] == target) return true;

        int mid = start + (end - start) / 2;
        if (nums[mid] == target) return true;

        if (nums[start] < nums[end]) {
            return (nums[mid] < target)
                ? search(nums, mid + 1, end - 1, target)
                : search(nums, start + 1, mid - 1, target);
        } else {
            if (nums[mid] > nums[start]) {
                return (nums[start] < target && target < nums[mid])
                    ? search(nums, start + 1, mid - 1, target)
                    : search(nums, mid + 1, end - 1, target);
            } else if (nums[mid] < nums[end]) {
                return (nums[mid] < target && target < nums[end])
                    ? search(nums, mid + 1, end - 1, target)
                    : search(nums, start + 1, mid - 1, target);
            } else {
                // [start] >= [mid] >= [end]
                return search(nums, start + 1, mid - 1, target)
                    || search(nums, mid + 1, end - 1, target);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {2, 5, 6, 0, 0, 1, 2};
        int target = 3;

        System.out.println(new SearchRotatedSortedArray2().search(nums, target));
    }
}
