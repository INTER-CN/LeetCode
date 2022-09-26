package com.gemini.leetcode.problems.tree;

/**
 * https://leetcode.cn/problems/populating-next-right-pointers-in-each-node-ii/
 * 二叉树，递归
 *
 * @Author Gemini
 * 2022-09-06
 **/
public class PopulatingNextRightPointers2 {

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
    }

    public Node connect(Node root) {
        if (root == null) return null;

        Node currentNode = root;
        Node n0 = null;
        Node n1;
        Node n2 = null;
        while (currentNode != null) {
            if (currentNode.left != null) {
                // visit node
                if (n0 == null) n0 = currentNode.left;
                n1 = n2;
                n2 = currentNode.left;
                if (n1 != null) n1.next = n2;
            }
            if (currentNode.right != null) {
                // visit node
                if (n0 == null) n0 = currentNode.right;
                n1 = n2;
                n2 = currentNode.right;
                if (n1 != null) n1.next = n2;
            }
            currentNode = currentNode.next;
        }

        connect(n0);

        return root;
    }
}
