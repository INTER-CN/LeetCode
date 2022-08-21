package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/sum-of-subarray-minimums/
 * 二分法
 * 时间复杂度O(n * logn)
 *
 * @Author Gemini
 * 2022-08-21
 **/
public class SumOfSubarrayMins {

    private static final long MODULO = 1_000_000_007L;

    public int sumSubarrayMins(int[] arr) {
        return (int) (sumMins(arr, 0, arr.length - 1) % MODULO);
    }

    private long sumMins(int[] arr, int start, int end) {
        if (start > end) return 0;
        if (start == end) return arr[start];

        long min = arr[start];
        int minIndex = start;
        for (int i = start + 1; i <= end; i++) {
            if (arr[i] < min) {
                min = arr[i];
                minIndex = i;
            }
        }

        int a = minIndex - start;
        int b = end - minIndex;

        return sumMins(arr, start, minIndex - 1) + sumMins(arr, minIndex + 1, end)
            + (a * b + a + b + 1) * min;
    }

    public static void main(String[] args) {
        int[] nums = {3, 1, 2, 4};
        System.out.println(new SumOfSubarrayMins().sumSubarrayMins(nums));
    }
}
