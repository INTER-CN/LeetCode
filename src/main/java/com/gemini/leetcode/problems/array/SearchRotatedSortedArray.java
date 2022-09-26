package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/search-in-rotated-sorted-array/
 * 二分法
 * 类似题目：
 * https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/
 * 一起在这儿做了
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

    public int findMin(int[] nums) {
        return findMin(nums, 0, nums.length - 1);
    }

    private int findMin(int[] nums, int start, int end) {
        if (start == end) return nums[start];
        if (nums[start] < nums[end]) return nums[start];
        if (start + 1 == end) return Math.min(nums[start], nums[end]);

        int mid = start + (end - start) / 2;
        if (nums[mid] > nums[start]) return findMin(nums, mid + 1, end);
        else return findMin(nums, start, mid);
    }

    public static void main(String[] args) {
        int[] nums = {4, 5, 6, 7, 0, 1, 2};
        int target = 4;
        System.out.println(new SearchRotatedSortedArray().search(nums, target));
        System.out.println(new SearchRotatedSortedArray().findMin(nums));
    }
}
