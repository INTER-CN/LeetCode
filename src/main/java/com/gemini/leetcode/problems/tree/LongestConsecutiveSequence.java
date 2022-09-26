package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/binary-tree-longest-consecutive-sequence/
 * 二叉树，递归水题
 *
 * @Author Gemini
 * 2022-08-31
 **/
public class LongestConsecutiveSequence {

    private int result;

    public int longestConsecutive(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        result = 1;
        recur(root);

        return result;
    }

    private int recur(TreeNode node) {
        if (node == null) return 0;
        if (node.left == null && node.right == null) return 1;

        int nodeResult = 1;
        if (node.left != null) {
            int leftResult = recur(node.left);
            if (node.left.val == node.val + 1) {
                nodeResult = leftResult + 1;
            }
        }
        if (node.right != null) {
            int rightResult = recur(node.right);
            if (node.right.val == node.val + 1 && rightResult + 1 > nodeResult) {
                nodeResult = rightResult + 1;
            }
        }

        if (nodeResult > result) result = nodeResult;
        return nodeResult;
    }
}
