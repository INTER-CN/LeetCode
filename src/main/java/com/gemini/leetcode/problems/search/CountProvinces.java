package com.gemini.leetcode.problems.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/number-of-provinces/
 * 图
 * BFS
 *
 * @Author Gemini
 * 2022-08-26
 **/
public class CountProvinces {

    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        int provinceCount = 0;
        int cityCount = 0;

        boolean[] flag = new boolean[n];
        int index = 0;
        while (cityCount < n) {
            // find first city
            while (flag[index]) index = next(index, n);
            provinceCount++;

            // search all other cities
            Queue<Integer> queue = new LinkedList<>();
            flag[index] = true;
            cityCount++;
            if (cityCount == n) return provinceCount;
            queue.add(index);
            while (!queue.isEmpty()) {
                index = queue.poll();
                for (int i = 0; i < n; i++) {
                    if (i == index) continue;
                    if (flag[i]) continue;
                    if (isConnected[index][i] == 0) continue;
                    flag[i] = true;
                    cityCount++;
                    if (cityCount == n) return provinceCount;
                    queue.add(i);
                }
            }
        }

        return provinceCount;
    }

    private int next(int index, int n) {
        return (index == n - 1) ? 0 : (index + 1);
    }

    public static void main(String[] args) {
        int[][] matrix = {
            {1, 0, 0},
            {0, 1, 0},
            {0, 0, 1}
        };
        System.out.println(new CountProvinces().findCircleNum(matrix));
    }
}
