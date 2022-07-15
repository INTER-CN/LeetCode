package com.gemini.leetcode.problems.easy;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/
 * 数组水题
 * 递归更方便
 *
 * @author 天何
 * @date 2022/7/12
 */
public class SpiralOrder {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return new int[0];

        int m = matrix.length;
        int n = matrix[0].length;

        int[] result = new int[m * n];
        spiralOrder(matrix, result, 0, 0, m - 1, 0, n - 1);

        return result;
    }

    private void spiralOrder(int[][] matrix, int[] result, int index, int x1, int x2, int y1, int y2) {
        if (x1 > x2 || y1 > y2) return;

        if (x1 == x2) {
            for (int i = y1; i <= y2; i++) result[index++] = matrix[x1][i];
            return;
        }

        if (y1 == y2) {
            for (int i = x1; i <= x2; i++) result[index++] = matrix[i][y1];
            return;
        }

        for (int i = y1; i < y2; i++) result[index++] = matrix[x1][i];
        for (int i = x1; i < x2; i++) result[index++] = matrix[i][y2];
        for (int i = y2; i > y1; i--) result[index++] = matrix[x2][i];
        for (int i = x2; i > x1; i--) result[index++] = matrix[i][y1];

        spiralOrder(matrix, result, index, x1 + 1, x2 - 1, y1 + 1, y2 - 1);
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12}
        };
        int[] result = new SpiralOrder().spiralOrder(matrix);
        System.out.println(new Gson().toJson(result));
    }
}
