package com.gemini.leetcode.problems;

/**
 * https://leetcode.cn/problems/target-sum/
 * 递归
 *
 * @author 天何
 * @date 2022/7/4
 */
public class TargetSum {

    public int findTargetSumWays(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return target == 0 ? 1 : 0;
        }

        return findTargetSumWays(nums, nums.length - 1, target);
    }

    private int findTargetSumWays(int[] nums, int index, int target) {
        if (index == 0) {
            int sumWay = 0;
            if (nums[0] == target) sumWay++;
            if (nums[0] + target == 0) sumWay++;
            return sumWay;
        }

        int sumWays1 = findTargetSumWays(nums, index - 1, target + nums[index]);
        int sumWays2 = findTargetSumWays(nums, index - 1, target - nums[index]);

        return sumWays1 + sumWays2;
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 1, 1};
        int target = 3;
        System.out.println(new TargetSum().findTargetSumWays(nums, target));
    }
}
