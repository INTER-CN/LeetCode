package com.gemini.leetcode.problems.logic;

/**
 * https://leetcode.cn/problems/divide-two-integers/
 * 二分法
 *
 * @Author Gemini
 * 2022-08-09
 **/
public class IntegerDivision {

    private static final int HALF_MAX = (int) Math.pow(2, 30);

    public int divide(int dividend, int divisor) {
        if (dividend == 0) return 0;
        if (divisor == 1) return dividend;
        if (dividend == divisor) return 1;
        if (dividend + divisor == 0) return -1;
        if (divisor == -1) {
            if (dividend == Integer.MIN_VALUE) return Integer.MAX_VALUE;
            else return -dividend;
        }

        if (dividend == Integer.MIN_VALUE) {
            if (divisor > 0) return -divideUnsignedInt(Math.abs(dividend + divisor), divisor) - 1;
            else return divideUnsignedInt(Math.abs(dividend - divisor), -divisor) + 1;
        }

        if (divisor == Integer.MIN_VALUE) return 0;

        boolean negative = dividend > 0 && divisor < 0 || dividend < 0 && divisor > 0;

        return negative
            ? -divideUnsignedInt(Math.abs(dividend), Math.abs(divisor))
            : divideUnsignedInt(Math.abs(dividend), Math.abs(divisor));
    }

    private int divideUnsignedInt(int dividend, int divisor) {
        if (dividend < divisor) return 0;

        int sum = divisor;
        int v = 1;
        while (sum < HALF_MAX && sum + sum < dividend) {
            sum = sum + sum;
            v = v + v;
        }

        return v + divideUnsignedInt(dividend - sum, divisor);
    }

    public static void main(String[] args) {
        System.out.println(new IntegerDivision().divide(-1010369383, -2147483648));
    }
}
