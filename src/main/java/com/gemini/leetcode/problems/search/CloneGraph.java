package com.gemini.leetcode.problems.search;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/clone-graph/
 * 图遍历
 *
 * @Author Gemini
 * 2022-09-13
 **/
public class CloneGraph {

    private class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private static final int MAX_VAL = 100;

    public Node cloneGraph(Node node) {
        if (node == null) return null;

        // 邻接矩阵
        boolean[][] edges = new boolean[MAX_VAL + 1][MAX_VAL + 1];
        boolean[] flag = new boolean[MAX_VAL + 1];

        Queue<Node> queue = new LinkedList<>();
        flag[node.val] = true;
        queue.add(node);
        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            for (Node nextNode : currentNode.neighbors) {
                edges[currentNode.val][nextNode.val] = true;
                edges[nextNode.val][currentNode.val] = true;
                if (!flag[nextNode.val]) {
                    flag[nextNode.val] = true;
                    queue.add(nextNode);
                }
            }
        }

        Node[] newNodes = new Node[MAX_VAL + 1];
        for (int i = 1; i <= MAX_VAL; i++) {
            if (!flag[i]) continue;
            newNodes[i] = new Node(i);
        }
        for (int i = 1; i <= MAX_VAL; i++) {
            if (!flag[i]) continue;
            for (int j = 1; j <= MAX_VAL; j++) {
                if (i == j) continue;
                if (!edges[i][j]) continue;
                newNodes[i].neighbors.add(newNodes[j]);
            }
        }

        return newNodes[node.val];
    }
}
