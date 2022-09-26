package com.gemini.leetcode.problems.easy;

/**
 * https://leetcode.cn/problems/count-primes/
 * 数组水题，所谓的埃氏筛法，暴力拿空间换时间
 *
 * @Author Gemini
 * 2022-08-26
 **/
public class CountPrimes {

    public int countPrimes(int n) {
        if (n <= 2) return 0;

        // false: 是质数, true: 不是质数
        boolean[] flag = new boolean[n];
        flag[0] = true;
        flag[1] = true;

        int countNonPrimes = 1;
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (flag[i]) continue;
            int j = i;
            while (i * j < n) {
                if (!flag[i * j]) {
                    flag[i * j] = true;
                    countNonPrimes++;
                }
                j++;
            }
        }

        return n - 1 - countNonPrimes;
    }

    public static void main(String[] args) {
        int n = 13;
        System.out.println(new CountPrimes().countPrimes(n));
    }
}
