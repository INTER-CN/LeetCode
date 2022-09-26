package com.gemini.leetcode.problems.tree;

/**
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node/
 * 完全二叉树，递归
 *
 * @Author Gemini
 * 2022-08-24
 **/
public class PopulatingNextRightPointers {

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };

    public Node connect(Node root) {
        if (root == null || root.left == null && root.right == null) return root;

        Node currentNode = root, nextNode = root.next;
        while (currentNode != null) {
            currentNode.left.next = currentNode.right;
            if (nextNode != null) currentNode.right.next = nextNode.left;
            currentNode = nextNode;
            if (currentNode == null) break;
            nextNode = currentNode.next;
        }

        connect(root.left);

        return root;
    }
}
