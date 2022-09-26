package com.gemini.leetcode.problems.presum;

/**
 * https://leetcode.cn/problems/range-sum-query-2d-immutable/
 * 二维前缀和
 *
 * @Author Gemini
 * 2022-08-27
 **/
public class NumMatrix {

    private int[][] preSum;

    public NumMatrix(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        preSum = new int[m][n];
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += matrix[i][0];
            preSum[i][0] = sum;
        }
        for (int j = 1; j < n; j++) preSum[0][j] = preSum[0][j - 1] + matrix[0][j];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (row1 == 0 && col1 == 0) return preSum[row2][col2];
        else if (row1 == 0) return preSum[row2][col2] - preSum[row2][col1 - 1];
        else if (col1 == 0) return preSum[row2][col2] - preSum[row1 - 1][col2];
        else return preSum[row2][col2] - preSum[row2][col1 - 1] - preSum[row1 - 1][col2] + preSum[row1 - 1][col1 - 1];
    }
}
