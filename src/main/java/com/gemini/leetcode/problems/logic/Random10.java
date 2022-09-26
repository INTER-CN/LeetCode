package com.gemini.leetcode.problems.logic;

import java.util.Random;

/**
 * https://leetcode.cn/problems/implement-rand10-using-rand7/
 * 数学逻辑
 *
 * @Author Gemini
 * 2022-09-19
 **/
public class Random10 {

    private Random random = new Random();

    public int rand7() {
        return random.nextInt(7) + 1;
    }

    public int rand10() {
        int a = rand7(), b = rand7();
        int value = (a - 1) * 7 + (b - 1);

        // 7 * 7 --> 49
        a = value % 10;
        if (value < 40) return a + 1;

        // 9 * 7 --> 63
        a++;
        b = rand7();
        value = (a - 1) * 9 + (b - 1);
        a = value % 10;
        if (value < 60) return a + 1;

        // 3 * 7 --> 21
        a++;
        b = rand7();
        value = (a - 1) * 3 + (b - 1);
        a = value % 10;
        if (value < 20) return a + 1;

        return rand10();
    }

    public static void main(String[] args) {
        Random10 random10 = new Random10();
        for (int i = 0; i < 10; i++) {
            System.out.println(random10.rand10());
        }
    }
}
