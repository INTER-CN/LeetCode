package com.gemini.leetcode.problems.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/max-area-of-island/
 * 广度优先遍历
 *
 * @Author Gemini
 * 2022-08-21
 **/
public class MaxAreaIsland {

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int maxAreaOfIsland(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        int[] position;
        int x, y;
        int maxArea = 0, area;
        while (true) {
            position = findOneIsland(grid, m, n);
            if (position == null) break;
            area = 0;
            grid[position[0]][position[1]] = 0;
            ++area;
            queue.add(position);
            while (!queue.isEmpty()) {
                int[] pos = queue.poll();
                for (int[] direction : DIRECTIONS) {
                    x = pos[0] + direction[0];
                    y = pos[1] + direction[1];
                    if (!inRange(m, n, x, y)) continue;
                    if (grid[x][y] != 1) continue;
                    grid[x][y] = 0;
                    ++area;
                    queue.add(new int[]{x, y});
                }
            }
            if (area > maxArea) maxArea = area;
        }

        return maxArea;
    }

    private int[] findOneIsland(int[][] grid, int m, int n) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) return new int[]{i, j};
            }
        }
        return null;
    }

    private boolean inRange(int m, int n, int i, int j) {
        return 0 <= i && i < m && 0 <= j && j < n;
    }

    public static void main(String[] args) {
        int[][] grid = {
            {0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
            {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}
        };
        System.out.println(new MaxAreaIsland().maxAreaOfIsland(grid));
    }

}
