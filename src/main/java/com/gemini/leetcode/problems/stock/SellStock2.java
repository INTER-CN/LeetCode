package com.gemini.leetcode.problems.stock;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/
 * 贪心水题
 *
 * @author 天何
 * @date 2022/7/9
 */
public class SellStock2 {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > prices[i - 1]) sum += (prices[i] - prices[i - 1]);
        }
        return sum;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(new SellStock2().maxProfit(prices));
    }
}
