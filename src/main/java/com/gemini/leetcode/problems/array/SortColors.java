package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/sort-colors/
 * 线性扫描
 *
 * @author 天何
 * @date 2022/7/17
 */
public class SortColors {

    public void sortColors(int[] nums) {
        if (nums == null || nums.length < 2) return;

        // left左边都是0，right右边都是2
        int i = 0, left = 0, right = nums.length - 1;
        while (i <= right) {
            if (nums[i] == 0) {
                swap(nums, i++, left++);
            } else if (nums[i] == 2) {
                swap(nums, i, right--);
            } else {
                ++i;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 1, 1, 0};
        new SortColors().sortColors(nums);
        System.out.println(new Gson().toJson(nums));
    }
}
