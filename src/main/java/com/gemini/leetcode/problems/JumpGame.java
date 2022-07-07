package com.gemini.leetcode.problems;

/**
 * https://leetcode.cn/problems/jump-game/
 * 贪心
 * 扫描过程实时存储当前能达到的最远位置
 *
 * @author 天何
 * @date 2022/6/30
 */
public class JumpGame {

    public boolean canJump(int[] nums) {
        if (nums == null || nums.length < 2) {
            return true;
        }

        // 能达到的最远index
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (i > farthest) {
                return false;
            }
            int currentFarthest = i + nums[i];
            farthest = Math.max(farthest, currentFarthest);
            if (farthest >= nums.length - 1) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(new JumpGame().canJump(nums));
    }
}
