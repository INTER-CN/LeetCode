package com.gemini.leetcode.problems.logic;

/**
 * https://leetcode.cn/problems/sum-of-two-integers/
 * 位操作
 *
 * @Author Gemini
 * 2022-08-21
 **/
public class SumOfTwoIntegers {

    public int getSum(int a, int b) {
        int sum = 0;

        int adder = 0, bitA, bitB;
        for (int i = 0; i < 32; i++) {
            bitA = (a >> i) & 1;
            bitB = (b >> i) & 1;
            if (bitA == 1 && bitB == 1) {
                if (adder == 1) {
                    sum ^= (1 << i);
                } else {
                    adder = 1;
                }
            } else if (bitA == 1 && bitB == 0 || bitA == 0 && bitB == 1) {
                if (adder == 0) {
                    sum ^= (1 << i);
                }
            } else {
                if (adder == 1) {
                    sum ^= (1 << i);
                    adder = 0;
                }
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        System.out.println(new SumOfTwoIntegers().getSum(6, -1));
    }
}
