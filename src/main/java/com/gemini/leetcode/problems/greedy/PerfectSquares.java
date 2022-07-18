package com.gemini.leetcode.problems.greedy;

/**
 * https://leetcode.cn/problems/perfect-squares/
 * 贪心 + 递归
 *
 * @author 天何
 * @date 2022/7/18
 */
public class PerfectSquares {

    public int numSquares(int n) {
        if (n < 4) return n;

        int[] nums = new int[n + 1];
        for (int i = 0; i < 4; i++) nums[i] = i;

        int x, square, count;
        for (int i = 4; i <= n; i++) {
            x = 2;
            square = x * x;
            nums[i] = i;
            while (square <= i) {
                count = 1 + nums[i - square];
                if (count < nums[i]) nums[i] = count;
                ++x;
                square = x * x;
            }
        }

        return nums[n];
    }

    public static void main(String[] args) {
        System.out.println(new PerfectSquares().numSquares(12));
    }
}
