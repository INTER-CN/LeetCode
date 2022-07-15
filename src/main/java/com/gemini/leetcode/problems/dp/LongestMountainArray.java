package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/longest-mountain-in-array/
 * 动态规划
 *
 * @author 天何
 * @date 2022/7/12
 */
public class LongestMountainArray {

    public int longestMountain(int[] arr) {
        if (arr == null || arr.length < 3) return 0;

        int n = arr.length;

        // ascending[i]: 以arr[i]结尾的最长递增子序列长度
        int[] ascending = new int[n];
        ascending[0] = 1;
        for (int i = 1; i < n - 1; i++) {
            ascending[i] = arr[i] > arr[i - 1] ? ascending[i - 1] + 1 : 1;
        }

        // descending[i]: 以arr[i]开始的最长递减子序列长度
        int[] descending = new int[n];
        descending[n - 1] = 1;
        for (int i = n - 2; i > 0; i--) {
            descending[i] = arr[i] > arr[i + 1] ? descending[i + 1] + 1 : 1;
        }

        int max = 0;
        for (int i = 1; i < n - 1; i++) {
            if (ascending[i] > 1 && descending[i] > 1) {
                max = Math.max(max, ascending[i] + descending[i] - 1);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 7, 3, 2, 5};
        System.out.println(new LongestMountainArray().longestMountain(arr));
    }
}
