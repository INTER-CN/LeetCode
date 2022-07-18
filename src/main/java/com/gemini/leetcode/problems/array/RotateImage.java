package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/rotate-image/
 * 数组旋转
 *
 * @author 天何
 * @date 2022/7/17
 */
public class RotateImage {

    public void rotate(int[][] matrix) {
        if (matrix == null || matrix.length < 2) return;

        int n = matrix.length;

        int x = n / 2;
        int y = n - x;

        int i2, j2, temp;
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                i2 = n - 1 - i;
                j2 = n - 1 - j;
                temp = matrix[i][j];
                matrix[i][j] = matrix[j2][i];
                matrix[j2][i] = matrix[i2][j2];
                matrix[i2][j2] = matrix[j][i2];
                matrix[j][i2] = temp;
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1,2,3},
            {4,5,6},
            {7,8,9}
        };
        new RotateImage().rotate(matrix);
        System.out.println(new Gson().toJson(matrix));
    }
}
