package com.gemini.leetcode.problems.dp;

/**
 * https://leetcode.cn/problems/largest-rectangle-in-histogram/
 * 动态规划
 * O(n^2) ... 会超时
 *
 * @author 天何
 * @date 2022/7/13
 */
public class Histogram {

    public int largestRectangleArea(int[] heights) {
        int n = heights.length;

        // left[i]: height[i]向左能走到的最远索引
        int[] left = new int[n];
        // right[i]: height[i]向右能走到的最远索引
        int[] right = new int[n];

        int index;

        left[0] = 0;
        for (int i = 1; i < n; i++) {
            if (heights[i - 1] < heights[i]) {
                left[i] = i;
            } else {
                index = i - 1;
                // 向左查找，直到 index - 1 指向的值小于当前值
                while (true) {
                    if (index == 0) break;
                    if (left[index] == index && heights[index - 1] >= heights[i]) {
                        index--;
                    } else if (left[index] == index) {
                        break;
                    } else {
                        index = left[index];
                    }
                    if (index == 0 || heights[index - 1] < heights[i]) break;
                }
                left[i] = index;
            }
        }

        right[n - 1] = n - 1;
        for (int i = n - 2; i >= 0; i--) {
            if (heights[i] > heights[i + 1]) {
                right[i] = i;
            } else {
                index = i + 1;
                // 向右查找，直到 index + 1 指向的值小于当前值
                while (true) {
                    if (index == n - 1) break;
                    if (right[index] == index && heights[index + 1] >= heights[i]) {
                        index++;
                    } else if (right[index] == index) {
                        break;
                    } else {
                        index = right[index];
                    }
                    if (index == n - 1 || heights[index + 1] < heights[i]) break;
                }
                right[i] = index;
            }
        }

        int area, max = 0;
        for (int i = 0; i < n; i++) {
            area = heights[i] * (right[i] - left[i] + 1);
            if (area > max) max = area;
        }

        return max;
    }

    public static void main(String[] args) {
        int[] heights = {2, 1, 5, 6, 2, 3};
        System.out.println(new Histogram().largestRectangleArea(heights));
    }
}
