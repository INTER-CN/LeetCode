package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/count-complete-tree-nodes/
 * 二叉树递归，O(logn)
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class CountCompleteTreeNodes {

    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;

        TreeNode LL = root.left;
        int leftDepth = 0;
        while (LL != null) {
            LL = LL.left;
            leftDepth++;
        }

        TreeNode RL = root.right;
        int rightDepth = 0;
        while (RL != null) {
            RL = RL.left;
            rightDepth++;
        }

        if (leftDepth == rightDepth) {
            return (int) Math.pow(2, leftDepth) + countNodes(root.right);
        } else {
            return countNodes(root.left) + (int) Math.pow(2, rightDepth);
        }
    }

    public static void main(String[] args) {
        TreeNode testTree = TreeNode.getTestTree();
        System.out.println(new CountCompleteTreeNodes().countNodes(testTree));
    }
}
