package com.gemini.leetcode.problems.easy;

/**
 * https://leetcode.cn/problems/sqrtx/
 * 二分法
 *
 * @Author Gemini
 * 2022-08-22
 **/
public class SqrtX {

    public int mySqrt(int x) {
        if (x < 2) return x;

        long v = x;

        int left = 1, right = x;
        long mid;
        while (true) {
            mid = left + (right - left) / 2;
            if (mid * mid <= v && v < (mid + 1) * (mid + 1)) return (int) mid;
            else if (mid * mid > v) right = (int) mid;
            else left = (int) mid;
        }
    }

    public static void main(String[] args) {
        System.out.println(new SqrtX().mySqrt(2147395599));
    }
}
