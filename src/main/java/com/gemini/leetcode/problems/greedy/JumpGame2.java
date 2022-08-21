package com.gemini.leetcode.problems.greedy;

/**
 * https://leetcode.cn/problems/jump-game-ii/
 * 贪心
 *
 * @Author Gemini
 * 2022-08-09
 **/
public class JumpGame2 {

    public int jump(int[] nums) {
        if (nums == null || nums.length < 2) return 0;

        int n = nums.length;

        int[] steps = new int[n];

        steps[n - 1] = 0;
        for (int i = n - 2; i >= 0; i--) {
            if (i + nums[i] >= n - 1) {
                steps[i] = 1;
            } else {
                int minStep = -1;
                for (int j = i + nums[i]; j > i; j--) {
                    if (minStep == -1 || steps[j] > 0 && steps[j] < minStep) minStep = steps[j];
                }
                steps[i] = minStep == -1 ? -1 : minStep + 1;
            }
        }

        return steps[0];
    }

    public static void main(String[] args) {
        int[] nums = {2, 0, 2, 0, 1};
        System.out.println(new JumpGame2().jump(nums));
    }
}
