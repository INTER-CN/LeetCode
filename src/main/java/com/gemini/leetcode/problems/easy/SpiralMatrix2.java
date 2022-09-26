package com.gemini.leetcode.problems.easy;

import com.google.gson.Gson;

/**
 * @Author Gemini
 * 2022-09-19
 **/
public class SpiralMatrix2 {

    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int value = 1;

        int start = 0, end = n - 1;
        while (start <= end) {
            if (start == end) {
                res[start][start] = value;
                break;
            }
            for (int j = start; j < end; j++) res[start][j] = value++;
            for (int i = start; i < end; i++) res[i][end] = value++;
            for (int j = end; j > start; j--) res[end][j] = value++;
            for (int i = end; i > start; i--) res[i][start] = value++;
            start++;
            end--;
        }

        return res;
    }

    public static void main(String[] args) {
        int[][] res = new SpiralMatrix2().generateMatrix(3);
        System.out.println(new Gson().toJson(res));
    }
}
