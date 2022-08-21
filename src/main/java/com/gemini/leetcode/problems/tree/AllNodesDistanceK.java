package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;
import com.gemini.leetcode.problems.tree.traverse.TraverseTool;

import java.util.*;

/**
 * https://leetcode.cn/problems/all-nodes-distance-k-in-binary-tree/
 * 树转图
 *
 * @Author Gemini
 * 2022-08-21
 **/
public class AllNodesDistanceK {

    class GraphNode {
        TreeNode treeNode;
        List<GraphNode> adjacentList;

        public GraphNode(TreeNode treeNode) {
            this.treeNode = treeNode;
            this.adjacentList = new LinkedList<>();
        }
    }

    private GraphNode targetGraphNode;

    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        // convert binary tree to graph
        convertToGraph(root, target);

        // traverse the graph by BFS
        List<Integer> result = new LinkedList<>();
        Queue<GraphNode> queue = new LinkedList<>();
        Queue<GraphNode> levelQueue = new LinkedList<>();
        Set<TreeNode> set = new HashSet<>();
        queue.add(targetGraphNode);
        for (int i = 0; i < k; i++) {
            while (!queue.isEmpty()) levelQueue.add(queue.poll());
            if (levelQueue.isEmpty()) break;
            while (!levelQueue.isEmpty()) {
                GraphNode graphNode = levelQueue.poll();
                set.add(graphNode.treeNode);
                for (GraphNode adjGraphNode : graphNode.adjacentList) {
                    if (!set.contains(adjGraphNode.treeNode)) queue.add(adjGraphNode);
                }
            }
        }
        while (!queue.isEmpty()) {
            GraphNode graphNode = queue.poll();
            result.add(graphNode.treeNode.val);
        }

        return result;
    }

    private GraphNode convertToGraph(TreeNode root, TreeNode target) {
        if (root == null) return null;
        GraphNode rootGraphNode = new GraphNode(root);
        if (root == target) targetGraphNode = rootGraphNode;

        if (root.left != null) {
            GraphNode leftGraphNode = convertToGraph(root.left, target);
            rootGraphNode.adjacentList.add(leftGraphNode);
            leftGraphNode.adjacentList.add(rootGraphNode);
        }

        if (root.right != null) {
            GraphNode rightGraphNode = convertToGraph(root.right, target);
            rootGraphNode.adjacentList.add(rightGraphNode);
            rightGraphNode.adjacentList.add(rootGraphNode);
        }

        return rootGraphNode;
    }

    public static void main(String[] args) {
        TreeNode testTree = TraverseTool.getTestTree();
        TreeNode target = testTree.left;
        System.out.println(new AllNodesDistanceK().distanceK(testTree, target, 1));
    }

}
