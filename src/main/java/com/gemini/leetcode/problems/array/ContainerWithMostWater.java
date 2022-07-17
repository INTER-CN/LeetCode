package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/container-with-most-water/
 * 双指针
 *
 * @author 天何
 * @date 2022/7/16
 */
public class ContainerWithMostWater {

    public int maxArea(int[] height) {
        if (height == null || height.length < 2) return 0;

        int max = 0, area;
        int left = 0, right = height.length - 1;
        while (left < right) {
            area = (right - left) * Math.min(height[left], height[right]);
            if (area > max) max = area;
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] height = {1, 8, 6, 2, 5, 4, 8, 3, 7};
        System.out.println(new ContainerWithMostWater().maxArea(height));
    }
}
