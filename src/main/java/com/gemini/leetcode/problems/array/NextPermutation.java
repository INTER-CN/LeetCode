package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/next-permutation/
 * 寻找"降序尾"
 *
 * @author 天何
 * @date 2022/7/16
 */
public class NextPermutation {

    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length < 2) return;

        int n = nums.length;

        // 从尾部找到降序序列
        int peakIndex = n - 1;
        while (peakIndex > 0 && nums[peakIndex - 1] >= nums[peakIndex]) peakIndex--;

        // 交换降序序列左侧（外）的元素和降序序列中比它大的最小元素
        int temp;
        if (peakIndex > 0) {
            int swapIndex = peakIndex;
            while (swapIndex < n - 1 && nums[peakIndex - 1] < nums[swapIndex + 1]) swapIndex++;

            // swap
            temp = nums[peakIndex - 1];
            nums[peakIndex - 1] = nums[swapIndex];
            nums[swapIndex] = temp;
        }

        // 翻转降序序列
        int left = peakIndex, right = n - 1;
        while (left < right) {
            // swap
            temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 3};
        NextPermutation nextPermutation = new NextPermutation();
        for (int i = 0; i < 3; i++) {
            nextPermutation.nextPermutation(nums);
            System.out.println(new Gson().toJson(nums));
        }
    }
}
