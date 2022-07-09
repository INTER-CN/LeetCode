package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

import java.util.*;

/**
 * https://leetcode.cn/problems/3sum/
 * 排序 + 二分法
 *
 * @author 天何
 * @date 2022/7/8
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();

        if (nums == null || nums.length < 3) return Collections.emptyList();

        int n = nums.length;

        quickSort(nums, 0, n - 1);

        // i: 最小元素索引, j: 最大元素索引, targetIndex: 中间元素索引
        // 用map做去重
        int target;
        Map<Integer, int[]> targetMap = new HashMap<>();
        for (int i = 0; i <= n - 3; i++) {
            for (int j = i + 2; j < n; j++) {
                if (nums[i] + nums[i + 1] + nums[j] > 0 || nums[i] + nums[j - 1] + nums[j] < 0) continue;
                target = 0 - nums[i] - nums[j];
                if (targetMap.containsKey(target)) {
                    int[] values = targetMap.get(target);
                    if (values[0] == nums[i]) continue;
                }
                int targetIndex = findTargetIndex(nums, i + 1, j - 1, target);
                if (targetIndex < 0) continue;
                List<Integer> listItem = new ArrayList<>();
                listItem.add(nums[i]);
                listItem.add(nums[targetIndex]);
                listItem.add(nums[j]);
                resultList.add(listItem);
                targetMap.put(target, new int[]{nums[i], nums[j]});
            }
        }

        return resultList;
    }

    private void quickSort(int[] nums, int start, int end) {
        if (start >= end) return;

        int left = start, right = end;
        while (left < right) {
            // 左指针不动，右指针左移
            while (left < right && nums[left] <= nums[right]) right--;
            if (left >= right) break;

            // 交换位置
            swap(nums, left, right);

            // 右指针不动，左指针右移
            while (left < right && nums[left] <= nums[right]) left++;
            if (left >= right) break;

            // 交换位置
            swap(nums, left, right);
        }

        quickSort(nums, start, left - 1);
        quickSort(nums, left + 1, end);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private int findTargetIndex(int[] nums, int start, int end, int target) {
        if (start > end) return -1;
        if (start == end && nums[start] != target) return -1;
        if (nums[start] == target) return start;
        if (nums[end] == target) return end;
        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            return mid;
        } else if (nums[mid] > target) {
            return findTargetIndex(nums, start + 1, mid - 1, target);
        } else {
            return findTargetIndex(nums, mid + 1, end - 1, target);
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = new ThreeSum().threeSum(nums);
        System.out.println(new Gson().toJson(result));
    }
}
