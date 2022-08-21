package com.gemini.leetcode.problems.search;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/snakes-and-ladders/
 * 题目描述又臭又长
 * 广度优先遍历
 *
 * @Author Gemini
 * 2022-08-18
 **/
public class SnakesAndLadders {

    public int snakesAndLadders(int[][] board) {
        int n = board.length;
        if (n < 3) return 1;

        int m = n * n;
        boolean[] flag = new boolean[m + 1];
        flag[1] = true;

        Queue<Integer> queue = new LinkedList<>();
        Queue<Integer> levelQueue = new LinkedList<>();
        int steps = 0;
        int next;
        int preCount, count = 1;
        queue.add(1);
        while (!queue.isEmpty()) {
            preCount = count;
            while (!queue.isEmpty()) levelQueue.add(queue.poll());
            while (!levelQueue.isEmpty()) {
                int label = levelQueue.poll();
                if (label + 6 >= m) return steps + 1;
                for (int i = 1; i <= 6; i++) {
                    next = label + i;
                    int dest = getDestination(board, n, next);
                    if (flag[dest]) continue;
                    if (dest == m) return steps + 1;
                    queue.add(dest);
                    flag[dest] = true;
                    count++;
                }
            }
            if (preCount == count) return -1;
            steps++;
        }

        return -1;
    }

    private int getDestination(int[][] board, int n, int label) {
        int i = n - 1 - (label - 1) / n;
        int j = (i + n) % 2 == 1 ? (label - 1) % n : n - 1 - (label - 1) % n;
        return board[i][j] > 0 ? board[i][j] : label;
    }

    public static void main(String[] args) {
        int[][] board = {
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 35, -1, -1, 13, -1},
            {-1, -1, -1, -1, -1, -1},
            {-1, 15, -1, -1, -1, -1}
        };
//        int[][] board = {
//            {1, 1, -1},
//            {1, 1, 1},
//            {-1, 1, 1}
//        };
        System.out.println(new SnakesAndLadders().snakesAndLadders(board));
    }
}
