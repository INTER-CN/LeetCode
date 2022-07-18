package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/kth-largest-element-in-an-array/
 * 快速排序的变种
 *
 * @author 天何
 * @date 2022/7/18
 */
public class FindKthLargest {

    public int findKthLargest(int[] nums, int k) {
        return findKthLargest(nums, 0, nums.length - 1, k);
    }

    private int findKthLargest(int[] nums, int start, int end, int k) {
        if (start == end) return nums[start];

        int left = start, right = end;
        while (left < right) {
            while (left < right && nums[left] >= nums[right]) --right;
            if (left == right) break;
            swap(nums, left, right);
            while (left < right && nums[left] >= nums[right]) ++left;
            if (left == right) break;
            swap(nums, left, right);
        }

        if (left - start + 1 == k) return nums[left];

        if (left - start + 1 > k) return findKthLargest(nums, start, left - 1, k);
        else return findKthLargest(nums, left + 1, end, k - (left - start + 1));
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        int k = 4;
        System.out.println(new FindKthLargest().findKthLargest(nums, k));
    }
}
