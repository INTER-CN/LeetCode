package com.gemini.leetcode.problems.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/rotting-oranges/
 * BFS
 *
 * @Author Gemini
 * 2022-09-05
 **/
public class RottingOranges {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    private boolean inRange(int m, int n, int i, int j) {
        return 0 <= i && i < m && 0 <= j && j < n;
    }

    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int levelCount = 0;
        int orangeCount = 0;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                switch (grid[i][j]) {
                    case 2:
                        queue.add(new int[]{i, j});
                        break;
                    case 1:
                        orangeCount++;
                        break;
                    default:
                        break;
                }
            }
        }

        Queue<int[]> levelQueue = new LinkedList<>();
        int x, y;
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) levelQueue.add(queue.poll());
            while (!levelQueue.isEmpty()) {
                int[] pos = levelQueue.poll();
                for (int[] direction : DIRECTIONS) {
                    x = pos[0] + direction[0];
                    y = pos[1] + direction[1];
                    if (!inRange(m, n, x, y)) continue;
                    if (grid[x][y] != 1) continue;
                    grid[x][y] = 2;
                    orangeCount--;
                    queue.add(new int[]{x, y});
                }
            }
            if (queue.isEmpty()) break;
            levelCount++;
        }

        if (orangeCount > 0) return -1;

        return levelCount;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {2, 1, 1},
            {1, 1, 0},
            {0, 1, 1}
        };
        System.out.println(new RottingOranges().orangesRotting(grid));
    }
}
