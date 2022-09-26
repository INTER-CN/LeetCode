package com.gemini.leetcode.problems.presum;

/**
 * https://leetcode.cn/problems/largest-1-bordered-square/
 * 二维前缀和
 *
 * @Author Gemini
 * 2022-08-27
 **/
public class Largest1BorderedSquare {

    public int largest1BorderedSquare(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] preSum = new int[m][n];
        init2DPreSum(preSum, grid, m, n);

        int maxLength = 0, len;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                len = Math.min(m - i, n - j);
                while (len > 0) {
                    if (len == 1) {
                        if (grid[i][j] == 1 && maxLength == 0) maxLength = 1;
                        break;
                    }
                    if (isValidSquare(preSum, i, j, len)) {
                        if (len > maxLength) maxLength = len;
                        break;
                    }
                    len--;
                }
            }
        }

        return maxLength * maxLength;
    }

    private void init2DPreSum(int[][] preSum, int[][] nums, int m, int n) {
        int sum = 0;
        for (int i = 0; i < m; i++) {
            sum += nums[i][0];
            preSum[i][0] = sum;
        }
        for (int j = 1; j < n; j++) preSum[0][j] = preSum[0][j - 1] + nums[0][j];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + nums[i][j];
            }
        }
    }

    private boolean isValidSquare(int[][] preSum, int i, int j, int len) {
        if (len == 2) return getSum(preSum, i, j, i + 1, j + 1) == 4;
        int row = i + len - 1;
        int col = j + len - 1;
        return 4 * len - 4 ==
            getSum(preSum, i, j, row, col) - getSum(preSum, i + 1, j + 1, row - 1, col - 1);
    }

    private int getSum(int[][] preSum, int row1, int col1, int row2, int col2) {
        if (row1 == 0 && col1 == 0) return preSum[row2][col2];
        else if (row1 == 0) return preSum[row2][col2] - preSum[row2][col1 - 1];
        else if (col1 == 0) return preSum[row2][col2] - preSum[row1 - 1][col2];
        else return preSum[row2][col2] - preSum[row2][col1 - 1] - preSum[row1 - 1][col2] + preSum[row1 - 1][col1 - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {
            {1, 1, 1},
            {1, 1, 1},
            {1, 1, 1}
        };
        System.out.println(new Largest1BorderedSquare().largest1BorderedSquare(grid));
    }
}
