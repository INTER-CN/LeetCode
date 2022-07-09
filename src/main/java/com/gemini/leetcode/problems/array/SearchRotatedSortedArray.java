package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/
 * 二分法
 *
 * @author 天何
 * @date 2022/7/1
 */
public class SearchRotatedSortedArray {

    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        return search(nums, target, 0, nums.length - 1);
    }

    private int search(int[] nums, int target, int start, int end) {
        if (start > end) {
            return -1;
        }

        if (target == nums[start]) {
            return start;
        }

        if (target == nums[end]) {
            return end;
        }

        if (end - start < 2) {
            return -1;
        }

        int mid = start + (end - start) / 2;
        if (target == nums[mid]) {
            return mid;
        }

        if (nums[start] < nums[mid]) {
            if (nums[start] < target && target < nums[mid]) {
                return search(nums, target, start + 1, mid - 1);
            } else {
                return search(nums, target, mid + 1, end - 1);
            }
        } else {
            if (nums[mid] < target && target < nums[end]) {
                return search(nums, target, mid + 1, end - 1);
            } else {
                return search(nums, target, start + 1, mid - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 4;
        System.out.println(new SearchRotatedSortedArray().search(nums, target));
    }
}
