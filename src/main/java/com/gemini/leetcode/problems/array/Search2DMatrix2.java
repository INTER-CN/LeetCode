package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/
 *
 * @author 天何
 * @date 2022/7/18
 */
public class Search2DMatrix2 {

    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            if (matrix[i][j] == target) return true;
            else if (matrix[i][j] > target) --i;
            else ++j;
        }

        return false;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        System.out.println(new Search2DMatrix2().searchMatrix(matrix, 20));
    }
}
