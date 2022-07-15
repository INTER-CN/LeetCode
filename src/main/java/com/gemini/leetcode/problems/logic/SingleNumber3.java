package com.gemini.leetcode.problems.logic;

/**
 * https://leetcode.cn/problems/single-number-iii/
 * https://leetcode.cn/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/
 *
 * @author 天何
 * @date 2022/7/14
 */
public class SingleNumber3 {

    public int[] singleNumber(int[] nums) {
        int x = 0;
        for (int num : nums) x ^= num;

        // 找到x的一个二进制为1的位置
        int i = 0;
        while (((x >> i) & 1) == 0) i++;

        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if (((num >> i) & 1) == 0) {
                num1 ^= num;
            } else {
                num2 ^= num;
            }
        }

        return new int[] {num1, num2};
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 3, 2, 5};
        int[] result = new SingleNumber3().singleNumber(nums);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
