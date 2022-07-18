package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/search-a-2d-matrix/
 * 二分法
 *
 * @author 天何
 * @date 2022/7/18
 */
public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        return searchMatrix(matrix, 0, m * n - 1, n, target);
    }

    private boolean searchMatrix(int[][] matrix, int start, int end, int n, int target) {
        if (start > end) return false;

        if (start == end) return getValue(matrix, start, n) == target;

        int mid = start + (end - start) / 2;
        int midValue = getValue(matrix, mid, n);

        if (midValue == target) return true;

        if (midValue > target) return searchMatrix(matrix, start, mid - 1, n, target);
        else return searchMatrix(matrix, mid + 1, end, n, target);
    }

    private int getValue(int[][] matrix, int k, int n) {
        int x = k / n;
        int y = k % n;
        return matrix[x][y];
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 3, 5, 7},
            {10, 11, 16, 20},
            {23, 30, 34, 60}
        };
        System.out.println(new Search2DMatrix().searchMatrix(matrix, 4));
    }
}
