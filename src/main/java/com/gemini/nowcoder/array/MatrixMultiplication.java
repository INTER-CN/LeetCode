package com.gemini.nowcoder.array;

import java.util.Scanner;

/**
 * HJ69 矩阵乘法
 * https://www.nowcoder.com/practice/ebe941260f8c4210aa8c17e99cbc663b?tpId=37&tqId=21292
 * 数组操作
 *
 * @author 天何
 * @date 2022/7/26
 */
public class MatrixMultiplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int x = scanner.nextInt();
        int y = scanner.nextInt();
        int z = scanner.nextInt();
        int[][] a = new int[x][y];
        int[][] b = new int[y][z];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                a[i][j] = scanner.nextInt();
            }
        }
        for (int i = 0; i < y; i++) {
            for (int j = 0; j < z; j++) {
                b[i][j] = scanner.nextInt();
            }
        }
        scanner.close();

        int[][] c = new int[x][z];
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < z; j++) {
                for (int k = 0; k < y; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < z; j++) {
                System.out.print(c[i][j] + " ");
            }
            System.out.println();
        }
    }
}
