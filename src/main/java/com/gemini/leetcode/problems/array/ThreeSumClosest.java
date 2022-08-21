package com.gemini.leetcode.problems.array;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/3sum-closest/
 * 排序 + 二分
 * 时间复杂度O(n^2 * logn)
 *
 * @Author Gemini
 * 2022-08-17
 **/
public class ThreeSumClosest {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);

        int n = nums.length;

        int closest = Integer.MAX_VALUE;
        int targetRemains, sum;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                targetRemains = target - nums[i] - nums[j];
                int searchResult = searchClosest(nums, j + 1, n - 1, targetRemains);
                if (searchResult == targetRemains) return target;
                sum = nums[i] + nums[j] + searchResult;
                if (Math.abs(sum - target) < Math.abs(closest - target)) closest = sum;
            }
        }

        return closest;
    }

    private int searchClosest(int[] nums, int start, int end, int target) {
        if (start == end) return nums[start];
        if (start + 1 == end) {
            return Math.abs(target - nums[start]) < Math.abs(target - nums[end]) ? nums[start] : nums[end];
        }
        if (target >= nums[end]) return nums[end];
        if (target <= nums[start]) return nums[start];

        int mid = start + (end - start) / 2;
        if (nums[mid] == target) {
            return nums[mid];
        } else if (nums[mid] < target) {
            int right = mid + 1;
            if (nums[right] > target && target - nums[mid] < nums[right] - target) return nums[mid];
            else return searchClosest(nums, right, end, target);
        } else {
            // nums[mid] > target
            int left = mid - 1;
            if (nums[left] < target && nums[mid] - target < target - nums[left]) return nums[mid];
            else return searchClosest(nums, start, left, target);
        }
    }

    public static void main(String[] args) {
        int[] nums = {-1, 2, 1, -4};
        int target = 2;
        System.out.println(new ThreeSumClosest().threeSumClosest(nums, target));
    }
}
