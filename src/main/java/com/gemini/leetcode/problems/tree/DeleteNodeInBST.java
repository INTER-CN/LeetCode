package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/delete-node-in-a-bst/
 * 二叉树
 *
 * @Author Gemini
 * 2022-09-13
 **/
public class DeleteNodeInBST {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        if (isLeaf(root) && root.val == key) return null;

        if (key < root.val) {
            TreeNode left = deleteNode(root.left, key);
            root.left = left;
        } else if (key > root.val) {
            TreeNode right = deleteNode(root.right, key);
            root.right = right;
        } else if (root.left != null) {
            int replaceValue = findPrevious(root);
            root.val = replaceValue;
        } else {
            // root.right != null
            int replaceValue = findNext(root);
            root.val = replaceValue;
        }

        return root;
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

    private int findPrevious(TreeNode root) {
        TreeNode p = root.left, q = root;
        while (p.right != null) {
            q = p;
            p = p.right;
        }

        if (q == root) q.left = p.left;
        else q.right = p.left;

        return p.val;
    }

    private int findNext(TreeNode root) {
        TreeNode p = root.right, q = root;
        while (p.left != null) {
            q = p;
            p = p.left;
        }

        if (q == root) q.right = p.right;
        else q.left = p.right;

        return p.val;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2, n1, n3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n7 = new TreeNode(7);
        TreeNode n6 = new TreeNode(6, n5, n7);
        TreeNode n4 = new TreeNode(4, n2, n6);

        TreeNode result = new DeleteNodeInBST().deleteNode(n4, 5);

        System.out.println(result);
    }
}
