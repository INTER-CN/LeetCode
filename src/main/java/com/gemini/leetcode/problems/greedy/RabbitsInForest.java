package com.gemini.leetcode.problems.greedy;

/**
 * https://leetcode.cn/problems/rabbits-in-forest/
 * 贪心
 *
 * @Author Gemini
 * 2022-08-26
 **/
public class RabbitsInForest {

    private static final int MAX_ANSWER = 1000;

    public int numRabbits(int[] answers) {
        int[] count = new int[MAX_ANSWER];
        int max = 0;
        for (int answer : answers) {
            ++count[answer];
            if (answer > max) max = answer;
        }

        // 回答不同的兔子一定颜色不同
        // 回答n的兔子有x只，则对应兔子个数是 ceiling(x / (n + 1)) * (n + 1)
        int sum = 0;
        sum += count[0];
        for (int n = 1; n <= max; n++) sum += (int) Math.ceil(count[n] / (double) (n + 1)) * (n + 1);

        return sum;
    }

    public static void main(String[] args) {
        int[] nums = {10, 10, 10};
        System.out.println(new RabbitsInForest().numRabbits(nums));
    }
}
