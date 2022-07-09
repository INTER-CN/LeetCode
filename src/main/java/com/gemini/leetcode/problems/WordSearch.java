package com.gemini.leetcode.problems;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/word-search/
 * 尝试了3种思路：
 * 深度优先遍历队列实现：超时
 * 动态规划：错误
 * 深度优先遍历递归实现：通过
 *
 * @author 天何
 * @date 2022/7/7
 */
public class WordSearch {

    class SearchSnapshot {
        public int x;
        public int y;
        public int index;
        public boolean[][] flag;
        public SearchDirection direction;

        public SearchSnapshot(int x, int y, int index, boolean[][] flag, int m, int n, SearchDirection direction) {
            this.x = x;
            this.y = y;
            this.index = index;
            this.flag = new boolean[m][n];
            copyBoard(flag, this.flag, m, n);
            this.direction = direction;
        }

        private void copyBoard(boolean[][] src, boolean[][] dst, int m, int n) {
            for (int i = 0; i < m; i++) for (int j = 0; j < n; j++) dst[i][j] = src[i][j];
        }
    }

    enum SearchDirection {
        NONE, UP, DOWN, LEFT, RIGHT
    }

    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] initFlag = new boolean[m][n];

        Queue<SearchSnapshot> searchSnapshotQueue = new LinkedList<>();

        char[] letters = word.toCharArray();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 定位首字母
                if (board[i][j] != letters[0]) continue;

                // 初始化标志位
                for (int p = 0; p < m; p++) for (int q = 0; q < n; q++) initFlag[p][q] = false;
                initFlag[i][j] = true;
                if (word.length() == 1) return true;

                // 搜索现场的快照入队
                searchSnapshotQueue.add(new SearchSnapshot(i, j, 0, initFlag, m, n, SearchDirection.NONE));
            }
        }

        // 遍历队列，逐字搜索
        while (!searchSnapshotQueue.isEmpty()) {
            SearchSnapshot snapshot = searchSnapshotQueue.poll();
            int x = snapshot.x;
            int y = snapshot.y;
            int index = snapshot.index;
            boolean[][] flag = snapshot.flag;
            SearchDirection direction = snapshot.direction;
            if (direction != SearchDirection.DOWN && x > 0 && flag[x - 1][y] == false
                && board[x - 1][y] == letters[index + 1]) {
                if (index + 1 == word.length() - 1) {
                    return true;
                } else {
                    SearchSnapshot nextSnapshot =
                        new SearchSnapshot(x - 1, y, index + 1, flag, m, n, SearchDirection.UP);
                    nextSnapshot.flag[x - 1][y] = true;
                    searchSnapshotQueue.add(nextSnapshot);
                }
            }
            if (direction != SearchDirection.UP && x + 1 < m && flag[x + 1][y] == false
                && board[x + 1][y] == letters[index + 1]) {
                if (index + 1 == word.length() - 1) {
                    return true;
                } else {
                    SearchSnapshot nextSnapshot =
                        new SearchSnapshot(x + 1, y, index + 1, flag, m, n, SearchDirection.DOWN);
                    nextSnapshot.flag[x + 1][y] = true;
                    searchSnapshotQueue.add(nextSnapshot);
                }
            }
            if (direction != SearchDirection.RIGHT && y > 0 && flag[x][y - 1] == false
                && board[x][y - 1] == letters[index + 1]) {
                if (index + 1 == word.length() - 1) {
                    return true;
                } else {
                    SearchSnapshot nextSnapshot =
                        new SearchSnapshot(x, y - 1, index + 1, flag, m, n, SearchDirection.LEFT);
                    nextSnapshot.flag[x][y - 1] = true;
                    searchSnapshotQueue.add(nextSnapshot);
                }
            }
            if (direction != SearchDirection.LEFT && y + 1 < n && flag[x][y + 1] == false
                && board[x][y + 1] == letters[index + 1]) {
                if (index + 1 == word.length() - 1) {
                    return true;
                } else {
                    SearchSnapshot nextSnapshot =
                        new SearchSnapshot(x, y + 1, index + 1, flag, m, n, SearchDirection.RIGHT);
                    nextSnapshot.flag[x][y + 1] = true;
                    searchSnapshotQueue.add(nextSnapshot);
                }
            }
        }

        return false;
    }

    public boolean exist2(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        int p = word.length();

        // dp[i][j][k]: 从board[i][j]出发，是否能搜索到word[k..]
        boolean dp[][][] = new boolean[m][n][p];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j][p - 1] = board[i][j] == word.charAt(p - 1);
                if (p == 1 && dp[i][j][p - 1]) return true;
            }
        }

        for (int k = p - 2; k >= 0; k--) {
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (board[i][j] != word.charAt(k)) {
                        dp[i][j][k] = false;
                    } else {
                        dp[i][j][k] = (i > 0 && dp[i - 1][j][k + 1])
                            || (i + 1 < m && dp[i + 1][j][k + 1])
                            || (j > 0 && dp[i][j - 1][k + 1])
                            || (j + 1 < n && dp[i][j + 1][k + 1]);
                    }
                    if (k == 0 && dp[i][j][k] == true) return true;
                }
            }
        }

        return false;
    }

    public boolean exist3(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        boolean[][] flag = new boolean[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 定位首字母
                if (board[i][j] != word.charAt(0)) continue;
                flag[i][j] = true;
                // 递归搜索
                boolean searchResult = search(board, flag, i, j, m, n, word, 0);
                if (searchResult) return true;
                flag[i][j] = false;
            }
        }

        return false;
    }

    private boolean search(char[][] board, boolean[][] flag, int i, int j, int m, int n, String word, int index) {
        // 已搜索至word[index]，坐标是[i][j]
        if (index == word.length() - 1) {
            return true;
        }

        // 向上搜索
        if (i > 0 && flag[i - 1][j] == false && board[i - 1][j] == word.charAt(index + 1)) {
            flag[i - 1][j] = true;
            boolean searchResult = search(board, flag, i - 1, j, m, n, word, index + 1);
            if (searchResult) return true;
            flag[i - 1][j] = false;
        }

        // 向下搜索
        if (i + 1 < m && flag[i + 1][j] == false && board[i + 1][j] == word.charAt(index + 1)) {
            flag[i + 1][j] = true;
            boolean searchResult = search(board, flag, i + 1, j, m, n, word, index + 1);
            if (searchResult) return true;
            flag[i + 1][j] = false;
        }

        // 向左搜索
        if (j > 0 && flag[i][j - 1] == false && board[i][j - 1] == word.charAt(index + 1)) {
            flag[i][j - 1] = true;
            boolean searchResult = search(board, flag, i, j - 1, m, n, word, index + 1);
            if (searchResult) return true;
            flag[i][j - 1] = false;
        }

        // 向右搜索
        if (j + 1 < n && flag[i][j + 1] == false && board[i][j + 1] == word.charAt(index + 1)) {
            flag[i][j + 1] = true;
            boolean searchResult = search(board, flag, i, j + 1, m, n, word, index + 1);
            if (searchResult) return true;
            flag[i][j + 1] = false;
        }

        return false;
    }

    public static void main(String[] args) {
        char[][] board = {
            {'A', 'A', 'A', 'A', 'A', 'A'},
            {'A', 'A', 'A', 'A', 'A', 'A'},
            {'A', 'A', 'A', 'A', 'A', 'A'},
            {'A', 'A', 'A', 'A', 'A', 'A'},
            {'A', 'A', 'A', 'A', 'A', 'A'},
            {'A', 'A', 'A', 'A', 'A', 'A'}
        };
        String word = "AAAAAAAAAAAAAAB";

        System.out.println(System.currentTimeMillis());
        System.out.println(new WordSearch().exist3(board, word));
        System.out.println(System.currentTimeMillis());
    }
}
