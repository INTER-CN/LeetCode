package com.gemini.leetcode.problems.stock;

/**
 * https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-iii/
 * 线性扫描
 *
 * @Author Gemini
 * 2022-08-20
 **/
public class SellStock3 {

    public int maxProfit(int[] prices) {
        int n = prices.length;

        // 先检查递增段的个数
        int greedyProfit = 0;
        int greedyCount = 0;
        int left = 0, right = 0;
        while (left < n) {
            while (right < n - 1 && prices[right] <= prices[right + 1]) right++;
            if (left < right) {
                greedyProfit += (prices[right] - prices[left]);
                greedyCount++;
            }
            left = right + 1;
            right = left;
        }
        if (greedyCount < 3) return greedyProfit;

        // 在[0..i]卖出的最大收益
        int[] profitL = new int[n];

        // 在[i..]买入的最大收益
        int[] profitR = new int[n];

        int profit;

        profitL[0] = 0;
        int currentMin = prices[0];
        for (int i = 1; i < n; i++) {
            profit = Math.max(prices[i] - currentMin, 0);
            profitL[i] = Math.max(profitL[i - 1], profit);
            if (prices[i] < currentMin) currentMin = prices[i];
        }

        profitR[n - 1] = 0;
        int currentMax = prices[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            profit = Math.max(currentMax - prices[i], 0);
            profitR[i] = Math.max(profitR[i + 1], profit);
            if (prices[i] > currentMax) currentMax = prices[i];
        }

        int maxProfit = 0, sum;
        for (int i = 1; i < n - 2; i++) {
            sum = profitL[i] + profitR[i + 1];
            if (sum > maxProfit) maxProfit = sum;
        }

        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices = {3, 3, 5, 0, 0, 3, 1, 4};
        System.out.println(new SellStock3().maxProfit(prices));
    }
}
