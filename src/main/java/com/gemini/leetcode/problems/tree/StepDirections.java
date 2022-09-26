package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

import java.util.*;

/**
 * https://leetcode.cn/problems/step-by-step-directions-from-a-binary-tree-node-to-another/
 * 树转图（超时）
 * 最近公共祖先 + DFS
 *
 * @Author Gemini
 * 2022-08-26
 **/
public class StepDirections {

    private class GraphNode {
        public int val;
        public List<GraphEdge> edgeList;
        // path from start node to current
        public String path;

        public GraphNode(int val) {
            this.val = val;
            this.edgeList = new LinkedList<>();
        }
    }

    private class GraphEdge {
        public GraphNode node;
        public char direction;

        public GraphEdge(GraphNode node, char direction) {
            this.node = node;
            this.direction = direction;
        }
    }

    public String getDirections(TreeNode root, int startValue, int destValue) {
        // construct graph
        Map<Integer, GraphNode> nodeMap = new HashMap<>();
        Stack<TreeNode> stack = new Stack<>();
        GraphNode rootNode = new GraphNode(root.val);
        nodeMap.put(rootNode.val, rootNode);
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode treeNode = stack.pop();

            // visit node
            GraphNode graphNode = nodeMap.get(treeNode.val);
            if (treeNode.right != null) {
                GraphNode rightNode = new GraphNode(treeNode.right.val);
                graphNode.edgeList.add(new GraphEdge(rightNode, 'R'));
                rightNode.edgeList.add(new GraphEdge(graphNode, 'U'));
                nodeMap.put(rightNode.val, rightNode);
                stack.push(treeNode.right);
            }
            if (treeNode.left != null) {
                GraphNode leftNode = new GraphNode(treeNode.left.val);
                graphNode.edgeList.add(new GraphEdge(leftNode, 'L'));
                leftNode.edgeList.add(new GraphEdge(graphNode, 'U'));
                nodeMap.put(leftNode.val, leftNode);
                stack.push(treeNode.left);
            }
        }

        // traverse graph by dfs
        GraphNode startNode = nodeMap.get(startValue);
        Queue<GraphNode> queue = new LinkedList<>();
        startNode.path = "";
        queue.add(startNode);
        while (!queue.isEmpty()) {
            GraphNode node = queue.poll();
            for (GraphEdge edge : node.edgeList) {
                edge.node.path = node.path + edge.direction;
                if (edge.node.val == destValue) return edge.node.path;
                queue.add(edge.node);
            }
        }

        return "";
    }

    private class PathNode {
        public TreeNode node;
        public String path;

        public PathNode(TreeNode node, String path) {
            this.node = node;
            this.path = path;
        }
    }

    public String getDirections2(TreeNode root, int startValue, int destValue) {
        TreeNode ancestor = findCommonAncestor(root, startValue, destValue);
        StringBuilder builder = new StringBuilder();

        // from start to ancestor
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<TreeNode> levelQueue = new LinkedList<>();
        queue.add(ancestor);
        int level = 0;
        boolean startFound = false;
        while (!queue.isEmpty()) {
            while (!queue.isEmpty()) levelQueue.add(queue.poll());
            while (!levelQueue.isEmpty()) {
                TreeNode node = levelQueue.poll();
                if (node.val == startValue) {
                    startFound = true;
                    break;
                }
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }
            if (startFound) break;
            level++;
        }

        for (int i = 0; i < level; i++) builder.append('U');

        // from ancestor to dest
        if (ancestor.val == destValue) return builder.toString();
        Queue<PathNode> nodeQueue = new LinkedList<>();
        nodeQueue.add(new PathNode(ancestor, ""));
        while (!nodeQueue.isEmpty()) {
            PathNode pathNode = nodeQueue.poll();
            TreeNode node = pathNode.node;
            if (node.left != null) {
                if (node.left.val == destValue) {
                    builder.append(pathNode.path);
                    builder.append('L');
                    break;
                } else {
                    nodeQueue.add(new PathNode(node.left, pathNode.path + 'L'));
                }
            }
            if (node.right != null) {
                if (node.right.val == destValue) {
                    builder.append(pathNode.path);
                    builder.append('R');
                    break;
                } else {
                    nodeQueue.add(new PathNode(node.right, pathNode.path + 'R'));
                }
            }
        }

        return builder.toString();
    }

    private TreeNode findCommonAncestor(TreeNode root, int v1, int v2) {
        if (root == null) return null;
        if (root.val == v1 || root.val == v2) return root;

        int leftCount = countNodes(root.left, v1, v2);
        if (leftCount == 2) return findCommonAncestor(root.left, v1, v2);
        else if (leftCount == 0) return findCommonAncestor(root.right, v1, v2);
        else return root;
    }

    private int countNodes(TreeNode root, int v1, int v2) {
        if (root == null) return 0;
        int countRoot = (root.val == v1 || root.val == v2) ? 1 : 0;
        return countRoot + countNodes(root.left, v1, v2) + countNodes(root.right, v1, v2);
    }

    public static void main(String[] args) {
        TreeNode testTree = TreeNode.getTestTree();
        System.out.println(new StepDirections().getDirections2(testTree, 4, 6));
    }
}
