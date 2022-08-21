package com.gemini;

public class Main {

    // 二维数组，遍历每个元素

    public void visitMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return;

        int m = matrix.length;
        int n = matrix[0].length;

        visitMatrixRecur(matrix, 0, m - 1, 0, n - 1);
    }

    public void visitMatrixRecur(int[][] matrix, int i1, int i2, int j1, int j2) {
        if (i1 > i2 || j1 > j2) return;
        if (i1 == i2) {
            for (int k = j1; k <= j2; k++) System.out.println(matrix[i1][k]);
            return;
        }
        if (j1 == j2) {
            for (int k = i1; k <= i2; k++) System.out.println(matrix[k][j1]);
            return;
        }

        for (int k = j1; k < j2; k++) System.out.println(matrix[i1][k]);
        for (int k = i1; k < i2; k++) System.out.println(matrix[k][j2]);
        for (int k = j2; k > j1; k--) System.out.println(matrix[i2][k]);
        for (int k = i2; k > i1; k--) System.out.println(matrix[k][j1]);

        visitMatrixRecur(matrix, i1 + 1, i2 - 1, j1 + 1, j2 - 1);
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 2, 3, 4},
            {11, 12, 13, 14},
            {21, 22, 23, 24},
            {31, 32, 33, 34}
        };
        new Main().visitMatrix(matrix);
    }

}
