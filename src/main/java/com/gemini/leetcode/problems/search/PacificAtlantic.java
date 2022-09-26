package com.gemini.leetcode.problems.search;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/pacific-atlantic-water-flow/
 * 二维数组
 * BFS
 *
 * @Author Gemini
 * 2022-08-28
 **/
public class PacificAtlantic {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;

        boolean[][] pFlag = new boolean[m][n];
        boolean[][] aFlag = new boolean[m][n];

        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            queue.add(new int[]{i, 0});
            pFlag[i][0] = true;
        }
        for (int j = 1; j < n; j++) {
            queue.add(new int[]{0, j});
            pFlag[0][j] = true;
        }

        searchAndFlag(queue, heights, m, n, pFlag);

        for (int i = 0; i < m; i++) {
            queue.add(new int[]{i, n - 1});
            aFlag[i][n - 1] = true;
        }
        for (int j = 0; j < n - 1; j++) {
            queue.add(new int[]{m - 1, j});
            aFlag[m - 1][j] = true;
        }

        searchAndFlag(queue, heights, m, n, aFlag);

        List<List<Integer>> result = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pFlag[i][j] && aFlag[i][j]) {
                    List<Integer> list = new LinkedList<>();
                    list.add(i);
                    list.add(j);
                    result.add(list);
                }
            }
        }
        return result;
    }

    private void searchAndFlag(Queue<int[]> queue, int[][] heights, int m, int n, boolean[][] flag) {
        int x, y;
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            for (int[] direction : DIRECTIONS) {
                x = cell[0] + direction[0];
                y = cell[1] + direction[1];
                if (!inRange(m, n, x, y)) continue;
                if (flag[x][y]) continue;
                if (heights[x][y] < heights[cell[0]][cell[1]]) continue;
                queue.add(new int[]{x, y});
                flag[x][y] = true;
            }
        }
    }

    private boolean inRange(int m, int n, int i, int j) {
        return 0 <= i && i < m && 0 <= j && j < n;
    }

    public static void main(String[] args) {
        int[][] heights = {
            {1, 2, 2, 3, 5},
            {3, 2, 3, 4, 4},
            {2, 4, 5, 3, 1},
            {6, 7, 1, 4, 5},
            {5, 1, 1, 2, 4}
        };
        List<List<Integer>> result = new PacificAtlantic().pacificAtlantic(heights);
        System.out.println(result);
    }

}
