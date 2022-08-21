package com.gemini.leetcode.problems.traverse;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/number-of-islands-ii/
 * 并查集
 *
 * @Author Gemini
 * 2022-08-21
 **/
public class NumberOfIslands2 {

    class UnionFind {

        private int[] parent;
        private int count;

        public UnionFind(int size) {
            this.parent = new int[size];
            for (int i = 0; i < size; i++) this.parent[i] = -1;
            this.count = 0;
        }

        public int getCount() {
            return this.count;
        }

        public void add(int x) {
            parent[x] = x;
            count++;
        }

        public int findRoot(int x) {
            while (x != parent[x]) x = parent[x];
            return x;
        }

        public boolean isUnited(int x, int y) {
            return findRoot(x) == findRoot(y);
        }

        public void union(int x, int y) {
            int rootX = findRoot(x);
            int rootY = findRoot(y);
            if (rootX == rootY) return;
            parent[rootY] = rootX;
            count--;
        }
    }

    private static final int[][] DIRECTIONS = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        List<Integer> result = new LinkedList<>();

        int size = m * n;
        UnionFind unionFind = new UnionFind(size);
        boolean[][] flag = new boolean[m][n];

        int x, y, index, x1, y1, index1;
        for (int[] position : positions) {
            x = position[0];
            y = position[1];
            if (flag[x][y]) {
                result.add(unionFind.getCount());
                continue;
            }

            index = x * n + y;
            unionFind.add(index);
            flag[x][y] = true;

            for (int[] direction : DIRECTIONS) {
                x1= position[0] + direction[0];
                y1 = position[1] + direction[1];
                if (!inRange(m, n, x1, y1)) continue;
                if (!flag[x1][y1]) continue;
                index1 = x1 * n + y1;
                if (!unionFind.isUnited(index, index1)) {
                    unionFind.union(index, index1);
                }
            }

            result.add(unionFind.getCount());
        }

        return result;
    }

    private boolean inRange(int m, int n, int x, int y) {
        return 0 <= x && x < m && 0 <= y && y < n;
    }

    public static void main(String[] args) {
        int m = 3;
        int n = 3;
        int[][] positions = {{0, 0}, {0, 1}, {1, 2}, {2, 1}, {1, 1}};
        System.out.println(new NumberOfIslands2().numIslands2(m, n, positions));
    }
}
