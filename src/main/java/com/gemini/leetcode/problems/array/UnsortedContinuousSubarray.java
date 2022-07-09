package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/shortest-unsorted-continuous-subarray/
 * 线性算法
 *
 * @author 天何
 * @date 2022/7/4
 */
public class UnsortedContinuousSubarray {

    public int findUnsortedSubarray(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        int leftIndex = findLeftIndex(nums);
        if (leftIndex == nums.length - 1) {
            return 0;
        }

        int rightIndex = findRightIndex(nums);

        return rightIndex - leftIndex - 1;
    }

    private int findLeftIndex(int[] nums) {
        int n = nums.length;

        // 找到从左端点开始的最长连续递增序列A
        int leftIncreasingEnd = 0;
        while (leftIncreasingEnd < n - 1 && nums[leftIncreasingEnd] <= nums[leftIncreasingEnd + 1]) leftIncreasingEnd++;
        if (leftIncreasingEnd == n - 1) return n - 1;

        // 找到后面的最小值x
        int remainingMin = Integer.MAX_VALUE;
        for (int i = leftIncreasingEnd + 1; i < n; i++) {
            if (nums[i] < remainingMin) {
                remainingMin = nums[i];
            }
        }

        // 扫描最长连续递增序列A，用最小值x进行截断
        int leftIndex = -1;
        while (nums[leftIndex + 1] <= remainingMin) leftIndex++;
        return leftIndex;
    }

    private int findRightIndex(int[] nums) {
        int n = nums.length;

        // 找到从右端点开始的最长连续递减序列B
        int rightDecreasingEnd = n - 1;
        while (rightDecreasingEnd > 0 && nums[rightDecreasingEnd - 1] <= nums[rightDecreasingEnd]) rightDecreasingEnd--;
        if (rightDecreasingEnd == 0) return 0;

        // 找到后面的最大值x
        int remainingMax = Integer.MIN_VALUE;
        for (int i = rightDecreasingEnd - 1; i >= 0; i--) {
            if (nums[i] > remainingMax) {
                remainingMax = nums[i];
            }
        }

        // 扫描最长连续递减序列B，用最大值x进行截断
        int rightIndex = n;
        while (nums[rightIndex - 1] >= remainingMax) rightIndex--;
        return rightIndex;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 3, 3};
        System.out.println(new UnsortedContinuousSubarray().findUnsortedSubarray(nums));
    }
}
