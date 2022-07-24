package com.gemini.nowcoder.search;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * HJ43 迷宫问题
 * https://www.nowcoder.com/practice/cf24906056f4488c9ddb132f317e03bc?tpId=37&tqId=21266
 * 广度优先遍历
 *
 * @author 天何
 * @date 2022/7/21
 */
public class Maze {

    static class Location {
        public int x;
        public int y;

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static boolean success;

    private static void search(int[][] maze, boolean[][] flag, int n, int m, Location location, List<Location> path) {
        int x = location.x;
        int y = location.y;
        if (x == n - 1 && y == m - 1) {
            for (Location point : path) {
                System.out.println("(" + point.x + "," + point.y + ")");
            }
            success = true;
            return;
        }

        int up = x - 1, down = x + 1, left = y - 1, right = y + 1;

        // 向上
        if (up >= 0 && maze[up][y] == 0 && flag[up][y] == false) {
            flag[up][y] = true;
            Location next = new Location(up, y);
            path.add(next);
            search(maze, flag, n, m, next, path);
            if (success) return;
            path.remove(path.size() - 1);
            flag[up][y] = false;
        }

        // 向下
        if (down < n && maze[down][y] == 0 && flag[down][y] == false) {
            flag[down][y] = true;
            Location next = new Location(down, y);
            path.add(next);
            search(maze, flag, n, m, next, path);
            if (success) return;
            path.remove(path.size() - 1);
            flag[down][y] = false;
        }

        // 向左
        if (left >= 0 && maze[x][left] == 0 && flag[x][left] == false) {
            flag[x][left] = true;
            Location next = new Location(x, left);
            path.add(next);
            search(maze, flag, n, m, next, path);
            if (success) return;
            path.remove(path.size() - 1);
            flag[x][left] = false;
        }

        // 向右
        if (right < m && maze[x][right] == 0 && flag[x][right] == false) {
            flag[x][right] = true;
            Location next = new Location(x, right);
            path.add(next);
            search(maze, flag, n, m, next, path);
            if (success) return;
            path.remove(path.size() - 1);
            flag[x][right] = false;
        }
    }

    public static void main(String[] args) throws Exception {
        // input
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        int[][] maze = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maze[i][j] = scanner.nextInt();
            }
        }
        scanner.close();

        boolean[][] flag = new boolean[n][m];
        Location init = new Location(0, 0);
        List<Location> path = new ArrayList<>();
        path.add(init);
        flag[0][0] = true;

        search(maze, flag, n, m, init, path);
    }
}
