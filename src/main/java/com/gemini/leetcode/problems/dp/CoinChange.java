package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/coin-change/
 * 动态规划
 *
 * @author 天何
 * @date 2022/7/19
 */
public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        // dp[i]: amount=i 的最优解
        int[] dp = new int[amount + 1];
        dp[0] = 0;
        int min, count;
        for (int i = 1; i <= amount; i++) {
            min = -1;
            for (int coin : coins) {
                if (i - coin >= 0 && dp[i - coin] >= 0) {
                    count = dp[i - coin] + 1;
                    if (min == -1 || min > count) min = count;
                }
            }
            dp[i] = min;
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int amount = 11;
        System.out.println(new CoinChange().coinChange(coins, amount));
    }
}
