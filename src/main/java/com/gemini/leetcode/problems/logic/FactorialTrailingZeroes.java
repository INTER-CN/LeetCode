package com.gemini.leetcode.problems.logic;

/**
 * https://leetcode.cn/problems/factorial-trailing-zeroes/
 * 数学题
 *
 * @Author Gemini
 * 2022-09-03
 **/
public class FactorialTrailingZeroes {

    public int trailingZeroes(int n) {
        int result = 0;
        while (n > 0) {
            n /= 5;
            result += n;
        }
        return result;
    }
}
