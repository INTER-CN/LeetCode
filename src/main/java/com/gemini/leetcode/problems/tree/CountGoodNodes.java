package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/count-good-nodes-in-binary-tree/
 * 二叉树递归
 *
 * @Author Gemini
 * 2022-08-24
 **/
public class CountGoodNodes {

    public int goodNodes(TreeNode root) {
        if (root == null) return 0;
        else return 1 + goodNodes(root.left, root.val) + goodNodes(root.right, root.val);
    }

    private int goodNodes(TreeNode node, int max) {
        if (node == null) return 0;

        if (node.val >= max) {
            return 1 + goodNodes(node.left, node.val) + goodNodes(node.right, node.val);
        } else {
            return goodNodes(node.left, max) +goodNodes(node.right, max);
        }
    }

    public static void main(String[] args) {
        TreeNode testTree = TreeNode.getTestTree();
        System.out.println(new CountGoodNodes().goodNodes(testTree));
    }
}
