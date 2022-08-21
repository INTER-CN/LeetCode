package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/counting-bits/
 * 二进制
 *
 * @Author Gemini
 * 2022-08-21
 **/
public class CountingBits {

    public int[] countBits(int n) {
        if (n == 0) return new int[]{0};
        else if (n == 1) return new int[]{0, 1};
        else if (n == 2) return new int[]{0, 1, 1};

        int[] result = new int[n + 1];
        result[0] = 0;
        result[1] = 1;
        result[2] = 1;

        int base = 2;
        for (int i = 3; i <= n; i++) {
            if (i < 2 * base) {
                result[i] = 1 + result[i - base];
            } else if (i == 2 * base) {
                result[i] = 1;
                base *= 2;
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(new Gson().toJson(new CountingBits().countBits(10)));
    }
}
