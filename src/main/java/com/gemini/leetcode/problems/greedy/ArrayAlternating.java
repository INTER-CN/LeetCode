package com.gemini.leetcode.problems.greedy;

/**
 * https://leetcode.cn/problems/minimum-operations-to-make-the-array-alternating/
 * 计数 + 贪心
 *
 * @Author Gemini
 * 2022-09-22
 **/
public class ArrayAlternating {

    private static final int MAX_VALUE = 100_000;

    public int minimumOperations(int[] nums) {
        if (nums == null || nums.length < 2) return 0;
        if (nums.length == 2) return (nums[0] == nums[1]) ? 1 : 0;

        int n = nums.length;

        int[] count = new int[MAX_VALUE + 1];
        int evenMainValue = 0;
        int evenMainCount = 0;
        for (int i = 0; i < n; i += 2) {
            count[nums[i]]++;
            if (count[nums[i]] > evenMainCount) {
                evenMainCount = count[nums[i]];
                evenMainValue = nums[i];
            }
        }

        count = new int[MAX_VALUE + 1];
        int oddMainValue = 0;
        int oddMainCount = 0;
        for (int i = 1; i < n; i += 2) {
            count[nums[i]]++;
            if (count[nums[i]] > oddMainCount) {
                oddMainCount = count[nums[i]];
                oddMainValue = nums[i];
            }
        }

        int evenNum = (n + 1) / 2;
        int oddNum = n / 2;

        if (evenMainValue != oddMainValue) return evenNum - evenMainCount + oddNum - oddMainCount;

        count = new int[MAX_VALUE + 1];
        int evenSecondValue = 0;
        int evenSecondCount = 0;
        for (int i = 0; i < n; i += 2) {
            if (nums[i] == evenMainValue) continue;
            count[nums[i]]++;
            if (count[nums[i]] > evenSecondCount) {
                evenSecondCount = count[nums[i]];
                evenSecondValue = nums[i];
            }
        }

        count = new int[MAX_VALUE + 1];
        int oddSecondValue = 0;
        int oddSecondCount = 0;
        for (int i = 1; i < n; i += 2) {
            if (nums[i] == oddMainValue) continue;
            count[nums[i]]++;
            if (count[nums[i]] > oddSecondCount) {
                oddSecondCount = count[nums[i]];
                oddSecondValue = nums[i];
            }
        }

        return Math.min(evenNum - evenMainCount + oddNum - oddSecondCount,
            evenNum - evenSecondCount + oddNum - oddMainCount);
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 2};
        System.out.println(new ArrayAlternating().minimumOperations(nums));
    }

}
