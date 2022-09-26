package com.gemini.leetcode.problems.array;

import com.google.gson.Gson;

/**
 * https://leetcode.cn/problems/diagonal-traverse/
 * 数组遍历
 *
 * @Author Gemini
 * 2022-08-30
 **/
public class DiagonalTraverse {

    public int[] findDiagonalOrder(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[] result = new int[m * n];
        int index = 0;

        int i = 0, j = 0;

        int limit = Math.min(m, n);
        boolean rightward = (m <= n); // otherwise, downward

        boolean rise = true;
        for (int len = 1; len < limit; len++) {
            result[index++] = matrix[i][j];
            if (rise) {
                for (int t = 1; t < len; t++) {
                    i--;
                    j++;
                    result[index++] = matrix[i][j];
                }
                j++;
            } else {
                for (int t = 1; t < len; t++) {
                    i++;
                    j--;
                    result[index++] = matrix[i][j];
                }
                i++;
            }
            rise = !rise;
        }

        for (int k = 0; k < Math.abs(m - n) + 1; k++) {
            result[index++] = matrix[i][j];
            if (rise) {
                for (int t = 1; t < limit; t++) {
                    i--;
                    j++;
                    result[index++] = matrix[i][j];
                }
            } else {
                for (int t = 1; t < limit; t++) {
                    i++;
                    j--;
                    result[index++] = matrix[i][j];
                }
            }
            rise = !rise;
            if (rightward) j++;
            else i++;
        }

        if (rightward) {
            if (j == n) {
                j--;
                i++;
            }
        } else {
            if (i == m) {
                i--;
                j++;
            }
        }

        for (int len = limit - 1; len >= 1; len--) {
            result[index++] = matrix[i][j];
            if (rise) {
                for (int t = 1; t < len; t++) {
                    i--;
                    j++;
                    result[index++] = matrix[i][j];
                }
                i++;
            } else {
                for (int t = 1; t < len; t++) {
                    i++;
                    j--;
                    result[index++] = matrix[i][j];
                }
                j++;
            }
            rise = !rise;
        }

        return result;
    }

    public static void main(String[] args) {
        int[][] matrix = {
            { 1,  2,  3,  4,  5},
            { 6,  7,  8,  9, 10},
            {11, 12, 13, 14, 15}
        };
        int[] result = new DiagonalTraverse().findDiagonalOrder(matrix);
        System.out.println(new Gson().toJson(result));
    }
}
