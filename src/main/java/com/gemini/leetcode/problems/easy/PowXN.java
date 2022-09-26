package com.gemini.leetcode.problems.easy;

/**
 * https://leetcode.cn/problems/powx-n/
 * 二分水题
 *
 * @Author Gemini
 * 2022-08-31
 **/
public class PowXN {

    public double myPow(double x, int n) {
        if (n == 0) return 1.0;
        if (n == 1) return x;
        if (n == -1) return 1.0 / x;

        int h = n / 2;
        double v = myPow(x, h);

        int i = n % 2;
        if (i == 0) return v * v;
        else return v * v * myPow(x, i);
    }

    public static void main(String[] args) {
        PowXN powXN = new PowXN();
        System.out.println(powXN.myPow(2.0, 10));
        System.out.println(powXN.myPow(2.1, 3));
        System.out.println(powXN.myPow(2.0, -2));
    }
}
