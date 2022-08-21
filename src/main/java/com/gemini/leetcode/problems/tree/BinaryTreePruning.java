package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/binary-tree-pruning/
 * 二叉树
 *
 * @Author Gemini
 * 2022-08-20
 **/
public class BinaryTreePruning {

    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return null;
        if (root.left == null && root.right == null && root.val == 0) return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (getSum(root) == 0) return null;
        else return root;
    }

    private int getSum(TreeNode root) {
        if (root == null) return 0;
        return root.val + getSum(root.left) + getSum(root.right);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(0);
        TreeNode n3 = new TreeNode(1);
        TreeNode n4 = new TreeNode(0);
        TreeNode n5 = new TreeNode(0);
        TreeNode n6 = new TreeNode(0);
        TreeNode n7 = new TreeNode(1);
        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        TreeNode result = new BinaryTreePruning().pruneTree(n1);

        System.out.println(result);
    }
}
