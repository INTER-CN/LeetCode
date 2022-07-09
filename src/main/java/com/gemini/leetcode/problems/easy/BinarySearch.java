package com.gemini.leetcode.problems.easy;

/**
 * https://leetcode.cn/problems/binary-search/
 * 二分水题
 *
 * @author 天何
 * @date 2022/7/5
 */
public class BinarySearch {

    public int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
        if (start > end) {
            return -1;
        }

        if (start == end) {
            return nums[start] == target ? start : -1;
        }

        if (nums[start] == target) {
            return start;
        }

        if (nums[end] == target) {
            return end;
        }

        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return binarySearch(nums, start + 1, mid - 1, target);
        } else {
            return binarySearch(nums, mid + 1, end - 1, target);
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 3, 5, 9, 12};
        int target = 9;
        System.out.println(new BinarySearch().search(nums, target));
    }
}
