package com.gemini.leetcode.problems.easy;

import com.google.gson.Gson;

/**
 * @Author Gemini
 * 2022-09-05
 **/
public class RotateArray {

    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n;
        if (k == 0) return;
        reverse(nums, 0, n - k - 1);
        reverse(nums, n - k, n - 1);
        reverse(nums, 0, n - 1);
    }

    private void reverse(int[] nums, int start, int end) {
        int i = start;
        int j = end;
        int temp;
        while (i < j) {
            temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
            i++;
            j--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7};
        int k = 3;
        new RotateArray().rotate(nums, k);
        System.out.println(new Gson().toJson(nums));
    }
}
