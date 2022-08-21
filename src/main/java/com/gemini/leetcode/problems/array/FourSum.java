package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/4sum/
 * 排序 + 线性扫描
 * 时间复杂度O(n^3)
 *
 * @Author Gemini
 * 2022-08-17
 **/
public class FourSum {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        int n = nums.length;
        if (n < 4) return Collections.emptyList();
        Arrays.sort(nums);

        List<List<Integer>> result = new LinkedList<>();

        int i = 0, j;
        long targetLong;
        while (i < n - 3) {
            j = i + 1;
            while (j < n - 2) {
                targetLong = (long) target - (long) nums[i] - (long) nums[j];
                result.addAll(searchSum(nums, j + 1, n - 1, nums[i], nums[j], targetLong));

                while (j < n - 2 && nums[j + 1] == nums[j]) j++;
                j++;
            }
            while (i < n - 3 && nums[i + 1] == nums[i]) i++;
            i++;
        }

        return result;
    }

    private List<List<Integer>> searchSum(int[] nums, int start, int end, int element0, int element1, long target) {
        List<List<Integer>> result = new LinkedList<>();

        int left = start, right = end;
        long sum;
        while (left < right) {
            sum = (long) nums[left] + (long) nums[right];
            if (sum < target) {
                while (left < right && (long) nums[left] + (long) nums[right] < target) left++;
                if (left == right) return result;
            } else if (sum > target) {
                while (left < right && (long) nums[left] + (long) nums[right] > target) right--;
                if (left == right) return result;
            } else {
                List<Integer> list = new LinkedList<>();
                list.add(element0);
                list.add(element1);
                list.add(nums[left]);
                list.add(nums[right]);
                result.add(list);
                while (left < right && nums[left] == nums[left + 1]) left++;
                left++;
                while (left < right && nums[right] == nums[right - 1]) right--;
                right--;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1000000000, 1000000000, 1000000000, 1000000000};
        int target = -294967296;
        List<List<Integer>> result = new FourSum().fourSum(nums, target);
        System.out.println(new Gson().toJson(result));
    }
}
