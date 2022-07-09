package com.gemini.leetcode.problems.stock;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * 动态规划
 * 多种状态的动态规划，重点还是定义dp函数的业务含义
 *
 * @author 天何
 * @date 2022/7/5
 */
public class SellStockWithCooldown {

    public int maxProfit(int[] prices) {
        int n = prices.length;

        /**
         * dp[i][0]: 第i天持股的最大收益
         * dp[i][1]: 第i天不持股，且第i天没有卖出的最大收益
         * dp[i][2]: 第i天不持股，且第i天有卖出的最大收益
         */
        int[][] dp = new int[n][3];

        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;

        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(
                dp[i - 1][0],
                dp[i - 1][1] - prices[i]
            );
            dp[i][1] = Math.max(
                dp[i - 1][1],
                dp[i - 1][2]
            );
            dp[i][2] = dp[i - 1][0] + prices[i];
        }

        return Math.max(dp[n - 1][1], dp[n - 1][2]);
    }

    public static void main(String[] args) {
        int[] prices = {1, 2, 4};
        System.out.println(new SellStockWithCooldown().maxProfit(prices));
    }
}
