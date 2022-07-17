package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/
 * 二分法
 *
 * @author 天何
 * @date 2022/7/16
 */
public class FindTargetRange {

    public int[] searchRange(int[] nums, int target) {
        if (nums == null || nums.length == 0) return new int[]{-1, -1};

        int n = nums.length;
        int left = findLeft(nums, 0, n - 1, target);
        if (left == -1) return new int[]{-1, -1};
        int right = findRight(nums, 0, n - 1, target);
        return new int[]{left, right};
    }

    private int findLeft(int[] nums, int start, int end, int target) {
        if (start > end) return -1;
        if (start == end) return nums[start] == target ? start : -1;
        if (start + 1 == end) {
            if (nums[start] == target) return start;
            if (nums[end] == target) return end;
            return -1;
        }

        if (nums[start] == target) return start;

        int mid = start + (end - start) / 2;
        if (nums[mid] == target && nums[mid - 1] < target) return mid;

        if (nums[mid] >= target) {
            return findLeft(nums, start + 1, mid - 1, target);
        } else {
            return findLeft(nums, mid + 1, end, target);
        }
    }

    private int findRight(int[] nums, int start, int end, int target) {
        if (start > end) return -1;
        if (start == end) return nums[start] == target ? start : -1;
        if (start + 1 == end) {
            if (nums[end] == target) return end;
            if (nums[start] == target) return start;
            return -1;
        }

        if (nums[end] == target) return end;

        int mid = start + (end - start) / 2;
        if (nums[mid] == target && nums[mid + 1] > target) return mid;

        if (nums[mid] <= target) {
            return findRight(nums, mid + 1, end - 1, target);
        } else {
            return findRight(nums, start, mid - 1, target);
        }
    }

    public static void main(String[] args) {
        int[] nums = {5, 7, 7, 8, 8, 10};
        int target = 8;
        int[] result = new FindTargetRange().searchRange(nums, target);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
