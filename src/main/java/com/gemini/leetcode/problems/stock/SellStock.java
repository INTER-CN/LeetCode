package com.gemini.leetcode.problems.stock;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/
 * 线性扫描
 *
 * @author 天何
 * @date 2022/7/9
 */
public class SellStock {

    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;

        int currentMin = prices[0];

        int diff, maxDiff = 0;
        for (int i = 1; i < prices.length; i++) {
            diff = prices[i] - currentMin;
            if (diff > maxDiff) maxDiff = diff;
            if (prices[i] < currentMin) currentMin = prices[i];
        }

        return maxDiff;
    }

    public static void main(String[] args) {
        int[] prices = {7, 1, 5, 3, 6, 4};
        System.out.println(new SellStock().maxProfit(prices));
    }
}
