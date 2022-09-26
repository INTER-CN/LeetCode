package com.gemini.leetcode.problems.easy;

/**
 * https://leetcode.cn/problems/bulls-and-cows/
 * 逻辑游戏
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class BullsAndCows {

    public String getHint(String secret, String guess) {
        int n = secret.length();

        int a = 0;
        int b = 0;
        int[] sCount = new int[10];
        int[] gCount = new int[10];
        for (int i = 0; i < n; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                a++;
            } else {
                sCount[secret.charAt(i) - '0']++;
                gCount[guess.charAt(i) - '0']++;
            }
        }
        for (int i = 0; i < 10; i++) b += Math.min(sCount[i], gCount[i]);

        return a + "A" + b + "B";
    }

    public static void main(String[] args) {
        String s = "1123";
        String g = "0111";
        System.out.println(new BullsAndCows().getHint(s, g));
    }
}
