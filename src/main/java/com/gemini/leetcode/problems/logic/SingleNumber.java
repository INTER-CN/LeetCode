package com.gemini.leetcode.problems.logic;

/**
 * https://leetcode.cn/problems/single-number/
 * 异或操作
 *
 * @author 天何
 * @date 2022/7/14
 */
public class SingleNumber {

    public int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result = result ^ num;
        }
        return result;
    }
}
