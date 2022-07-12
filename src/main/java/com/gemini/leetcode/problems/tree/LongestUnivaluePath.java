package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/longest-univalue-path/
 * 二叉树递归
 *
 * @author 天何
 * @date 2022/7/9
 */
public class LongestUnivaluePath {

    public int longestUnivaluePath(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return 0;

        int lengthAsEnd = longestUnivaluePathWithRootAsEnd(root);
        int leftLength = longestUnivaluePath(root.left);
        int rightLength = longestUnivaluePath(root.right);
        int lengthAsEdge =
            (root.left != null && root.left.val == root.val && root.right != null && root.right.val == root.val)
            ? longestUnivaluePathWithRootAsEnd(root.left) + longestUnivaluePathWithRootAsEnd(root.right) + 2 : 0;

        return Math.max(Math.max(lengthAsEnd, lengthAsEdge), Math.max(leftLength, rightLength));
    }

    /**
     * 以root为端点的最长同值路径
     */
    private int longestUnivaluePathWithRootAsEnd(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return 0;

        int leftLength =
            (root.left != null && root.left.val == root.val) ? longestUnivaluePathWithRootAsEnd(root.left) + 1 : 0;
        int rightLength =
            (root.right != null && root.right.val == root.val) ? longestUnivaluePathWithRootAsEnd(root.right) + 1 : 0;

        return Math.max(leftLength, rightLength);
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(4);
        TreeNode n3 = new TreeNode(4);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(4);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;

        System.out.println(new LongestUnivaluePath().longestUnivaluePath(n1));
    }
}
