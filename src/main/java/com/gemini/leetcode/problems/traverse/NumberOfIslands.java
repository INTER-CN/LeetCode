package com.gemini.leetcode.problems.traverse;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/number-of-islands/
 * 广度优先遍历：使用队列
 *
 * @author 天何
 * @date 2022/7/7
 */
public class NumberOfIslands {

    class IslandLocation {
        public int x;
        public int y;

        public IslandLocation(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public int numIslands(char[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int count = 0;
        while (true) {
            IslandLocation location = pickIsland(grid, m, n);
            if (location == null) return count;
            eatIsland(grid, m, n, location);
            count++;
        }
    }

    private IslandLocation pickIsland(char[][] grid, int m, int n) {
        for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) if (grid[i][j] == '1') return new IslandLocation(i, j);
        return null;
    }

    private void eatIsland(char[][] grid, int m, int n, IslandLocation landingLocation) {
        Queue<IslandLocation> locationQueue = new LinkedList<>();
        locationQueue.add(landingLocation);
        grid[landingLocation.x][landingLocation.y] = '0';
        while (!locationQueue.isEmpty()) {
            IslandLocation location = locationQueue.poll();
            int x = location.x;
            int y = location.y;
            // 向上
            if (x > 0 && grid[x - 1][y] == '1') {
                locationQueue.add(new IslandLocation(x - 1, y));
                grid[x - 1][y] = '0';
            }
            // 向下
            if (x + 1 < m && grid[x + 1][y] == '1') {
                locationQueue.add(new IslandLocation(x + 1, y));
                grid[x + 1][y] = '0';
            }
            // 向左
            if (y > 0 && grid[x][y - 1] == '1') {
                locationQueue.add(new IslandLocation(x, y - 1));
                grid[x][y - 1] = '0';
            }
            // 向右
            if (y + 1 < n && grid[x][y + 1] == '1') {
                locationQueue.add(new IslandLocation(x, y + 1));
                grid[x][y + 1] = '0';
            }
        }
    }

    public static void main(String[] args) {
        char[][] grid = {
            {'1', '0', '1', '0', '0'},
            {'1', '1', '0', '1', '0'},
            {'1', '1', '0', '0', '0'},
            {'1', '1', '0', '0', '0'}
        };
        System.out.println(new NumberOfIslands().numIslands(grid));
    }
}
