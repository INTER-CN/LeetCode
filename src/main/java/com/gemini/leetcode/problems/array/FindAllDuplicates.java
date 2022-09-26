package com.gemini.leetcode.problems.array;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/find-all-duplicates-in-an-array/
 * 数组逻辑处理
 *
 * @Author Gemini
 * 2022-09-23
 **/
public class FindAllDuplicates {

    public List<Integer> findDuplicates(int[] nums) {
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            while (nums[i] != nums[nums[i] - 1]) swap(nums, i, nums[i] - 1);
        }

        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) result.add(nums[i]);
        }

        return result;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        System.out.println(new FindAllDuplicates().findDuplicates(nums));
    }
}
