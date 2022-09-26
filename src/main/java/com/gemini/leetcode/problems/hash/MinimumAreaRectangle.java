package com.gemini.leetcode.problems.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.cn/problems/minimum-area-rectangle/
 * 哈希集合
 * 坐标序列化为字符串
 *
 * @Author Gemini
 * 2022-08-28
 **/
public class MinimumAreaRectangle {

    public int minAreaRect(int[][] points) {
        int n = points.length;
        Set<String> set = new HashSet<>();
        for (int[] point : points) set.add(convertToString(point));

        int result = 0;
        int area;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (points[i][0] == points[j][0] || points[i][1] == points[j][1]) continue;
                if (!set.contains(convertToString(points[i][0], points[j][1]))) continue;
                if (!set.contains(convertToString(points[j][0], points[i][1]))) continue;
                area = Math.abs(points[i][0] - points[j][0]) * Math.abs(points[i][1] - points[j][1]);
                if (result == 0 || area < result) result = area;
            }
        }

        return result;
    }

    private String convertToString(int[] point) {
        return point[0] + "," + point[1];
    }

    private String convertToString(int x, int y) {
        return x + "," + y;
    }

    public static void main(String[] args) {
        int[][] points = {
            {1, 1}, {1, 3}, {3, 1}, {3, 3}, {4, 1}, {4, 3}
        };
        System.out.println(new MinimumAreaRectangle().minAreaRect(points));
    }
}
