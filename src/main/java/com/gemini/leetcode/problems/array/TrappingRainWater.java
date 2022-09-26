package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/trapping-rain-water/
 * 数组线性扫描
 *
 * @Author Gemini
 * 2022-08-22
 **/
public class TrappingRainWater {

    public int trap(int[] height) {
        if (height == null || height.length < 2) return 0;

        int n = height.length;

        // 找到最大值
        int maxValue = height[0];
        int maxIndex = 0;
        int sum = height[0];
        for (int i = 1; i < n; i++) {
            sum += height[i];
            if (height[i] > maxValue) {
                maxValue = height[i];
                maxIndex = i;
            }
        }

        int missingWater = 0;

        // 从左端点向右扫到最大值，计算漏多少水
        int currentMax = 0;
        for (int i = 0; i < maxIndex; i++) {
            if (height[i] > currentMax) currentMax = height[i];
            missingWater += (maxValue - currentMax);
        }

        // 从右端点向左扫到最大值，计算漏多少水
        currentMax = 0;
        for (int i = n - 1; i > maxIndex; i--) {
            if (height[i] > currentMax) currentMax = height[i];
            missingWater += (maxValue - currentMax);
        }

        // 结果 = 最大值 * n - 漏水数 - 数组和
        return maxValue * n - missingWater - sum;
    }

    public static void main(String[] args) {
        int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(new TrappingRainWater().trap(height));
    }
}
