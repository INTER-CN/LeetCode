package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/sort-an-array/
 * 写个快排，基础快排超时，加个median-of-three
 *
 * @Author Gemini
 * 2022-09-19
 **/
public class ArraySorting {

    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;
        if (start + 1 == end) {
            if (nums[start] > nums[end]) swap(nums, start, end);
            return;
        }

        int pivotIndex = selectPivotIndex(nums, start, end);
        if (pivotIndex != start) swap(nums, start, pivotIndex);

        int left = start, right = end;
        while (left < right) {
            while (left < right && nums[left] <= nums[right]) right--;
            if (left == right) break;
            swap(nums, left, right);
            while (left < right && nums[left] <= nums[right]) left++;
            if (left == right) break;
            swap(nums, left, right);
        }

        quickSort(nums, start, left - 1);
        quickSort(nums, left + 1, end);
    }

    private int selectPivotIndex(int[] nums, int start, int end) {
        int mid = start + (end - start) / 2;
        if (nums[mid] <= nums[start] && nums[start] <= nums[end]
            || nums[end] <= nums[start] && nums[start] <= nums[mid]) {
            return start;
        } else if (nums[start] <= nums[mid] && nums[mid] <= nums[end]
            || nums[end] <= nums[mid] && nums[mid] <= nums[start]) {
            return mid;
        } else {
            return end;
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {5, 1, 1, 2, 0, 0};
        new ArraySorting().sortArray(nums);
        System.out.println(new Gson().toJson(nums));
    }
}
