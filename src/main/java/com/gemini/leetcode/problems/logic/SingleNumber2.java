package com.gemini.leetcode.problems.logic;

/**
 * https://leetcode.cn/problems/single-number-ii/
 * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-ii-lcof/
 * 位操作
 *
 * @author 天何
 * @date 2022/7/14
 */
public class SingleNumber2 {

    public int singleNumber(int[] nums) {
        int sum, result = 0;
        for (int i = 0; i < 32; i++) {
            sum = 0;
            for (int num : nums) {
                sum += ((num >> i) & 1);
            }
            if (sum % 3 > 0) {
                result |= (1 << i);
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {2, 2, 2, 3};
        System.out.println(new SingleNumber2().singleNumber(nums));
    }
}
