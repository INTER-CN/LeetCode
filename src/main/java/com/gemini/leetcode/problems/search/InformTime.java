package com.gemini.leetcode.problems.search;

import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/time-needed-to-inform-all-employees/
 * 树的深度优先搜索（DFS）
 *
 * @Author Gemini
 * 2022-08-31
 **/
public class InformTime {

    private class GraphNode {
        public int time;
        public List<GraphNode> list;

        public GraphNode(int time) {
            this.time = time;
            this.list = new LinkedList<>();
        }
    }

    private int maxTime;

    public int numOfMinutes(int n, int headId, int[] manager, int[] informTime) {
        if (n == 1) return 0;

        GraphNode[] nodes = new GraphNode[n];
        for (int i = 0; i < n; i++) nodes[i] = new GraphNode(informTime[i]);

        for (int i = 0; i < n; i++) {
            if (i == headId) continue;
            nodes[manager[i]].list.add(nodes[i]);
        }

        maxTime = 0;
        dfs(nodes[headId], 0);

        return maxTime;
    }

    private void dfs(GraphNode node, int currentTime) {
        if (node.list.isEmpty()) {
            if (currentTime > maxTime) maxTime = currentTime;
            return;
        }

        for (GraphNode childNode : node.list) {
            dfs(childNode, currentTime + node.time);
        }
    }

    public static void main(String[] args) {
        int n = 9;
        int headId = 2;
        int[] manager = {2, 2, -1, 0, 0, 1, 1, 3, 3};
        int[] informTime = {1, 1, 1, 1, 0, 0, 0, 0, 0};
        System.out.println(new InformTime().numOfMinutes(n, headId, manager, informTime));
    }
}
