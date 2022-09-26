package com.gemini.leetcode.problems.array;

/**
 * https://leetcode.cn/problems/search-a-2d-matrix-ii/
 * https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/
 *
 * @author 天何
 * @date 2022/7/18
 */
public class Search2DMatrix2 {

    /**
     * 线性查找
     */
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

    /**
     * 二分查找
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null) return false;
        int m = matrix.length;
        if (m == 0) return false;
        int n = matrix[0].length;
        if (n == 0) return false;

        int i = m - 1, j = 0;
        while (i >= 0 && j < n) {
            int[] pos = binSearch(matrix, i, j, m, n, target);
            if (pos[0] >= 0 && pos[0] < m && pos[1] >= 0 && pos[1] < n) {
                i = pos[0];
                j = pos[1];
            } else {
                return false;
            }
            if (matrix[i][j] == target) return true;
        }

        return false;
    }

    private int[] binSearch(int[][] matrix, int i, int j, int m, int n, int target) {
        if (matrix[i][j] == target) return new int[]{i, j};
        if (matrix[i][j] < target) {
            if (matrix[i][n - 1] < target) return new int[]{-1, -1};
            int start = j, end = n - 1;
            int mid;
            while (true) {
                if (start == end) return new int[]{i, start};
                if (start + 1 == end) {
                    if (matrix[i][start] < target && target <= matrix[i][end]) return new int[]{i, end};
                    else return new int[]{i, start};
                }
                mid = start + (end - start) / 2;
                if (matrix[i][mid - 1] < target && target <= matrix[i][mid]) {
                    return new int[]{i, mid};
                } else if (matrix[i][mid] < target) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        } else {
            if (matrix[0][j] > target) return new int[]{-1, -1};
            int start = 0, end = i;
            int mid;
            while (true) {
                if (start == end) return new int[]{start, j};
                if (start + 1 == end) {
                    if (matrix[start][j] <= target && target < matrix[end][j]) return new int[]{start, j};
                    else return new int[]{end, j};
                }
                mid = start + (end - start) / 2;
                if (matrix[mid][j] <= target && target < matrix[mid + 1][j]) {
                    return new int[]{mid, j};
                } else if (matrix[mid][j] > target) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 4, 7, 11, 15},
            {2, 5, 8, 12, 19},
            {3, 6, 9, 16, 22},
            {10, 13, 14, 17, 24},
            {18, 21, 23, 26, 30}
        };
        Search2DMatrix2 obj = new Search2DMatrix2();
        for (int i = 0; i <= 31; i++) {
            System.out.println("[" + i + "]" + obj.findNumberIn2DArray(matrix, i));
        }
    }
}
