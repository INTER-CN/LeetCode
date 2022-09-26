package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/binary-tree-maximum-path-sum/
 * 二叉树递归
 *
 * @Author Gemini
 * 2022-08-22
 **/
public class MaximumPathSum {

    private int maxSum;

    public int maxPathSum(TreeNode root) {
        // init value
        maxSum = root.val;
        // recursively calculate
        maxSum = maxSum(root);
        return maxSum;
    }

    private int maxSum(TreeNode root) {
        if (root.left == null && root.right == null) {
            if (root.val > maxSum) maxSum = root.val;
            return root.val;
        } else if (root.right == null) {
            int leftSum = maxSumWithRootAsEnd(root.left);
            int leftMaxSum = maxSum(root.left);
            int maxSumWithRoot = leftSum > 0 ? (root.val + leftSum) : root.val;
            int max = Math.max(maxSumWithRoot, leftMaxSum);
            if (max > maxSum) maxSum = max;
            return max;
        } else if (root.left == null) {
            int rightSum = maxSumWithRootAsEnd(root.right);
            int rightMaxSum = maxSum(root.right);
            int maxSumWithRoot = rightSum > 0 ? (root.val + rightSum) : root.val;
            int max = Math.max(maxSumWithRoot, rightMaxSum);
            if (max > maxSum) maxSum = max;
            return max;
        } else {
            int leftSum = maxSumWithRootAsEnd(root.left);
            int rightSum = maxSumWithRootAsEnd(root.right);
            int leftMaxSum = maxSum(root.left);
            int rightMaxSum = maxSum(root.right);
            int maxSumWithRoot = root.val + getMax(0, leftSum, rightSum, leftSum + rightSum);
            int max = getMax(maxSumWithRoot, leftMaxSum, rightMaxSum);
            if (max > maxSum) maxSum = max;
            return max;
        }
    }

    private int maxSumWithRootAsEnd(TreeNode root) {
        int leftSum = root.left == null ? 0 : maxSumWithRootAsEnd(root.left);
        int rightSum = root.right == null ? 0 : maxSumWithRootAsEnd(root.right);
        return root.val + getMax(0, leftSum, rightSum);
    }

    private static int getMax(Integer... values) {
        int max = Integer.MIN_VALUE;
        for (Integer value : values) {
            if (value > max) max = value;
        }
        return max;
    }

    public static void main(String[] args) {
        TreeNode n9 = new TreeNode(1);
        TreeNode n8 = new TreeNode(2);
        TreeNode n7 = new TreeNode(7);
        TreeNode n6 = new TreeNode(4, null, n9);
        TreeNode n5 = new TreeNode(13);
        TreeNode n4 = new TreeNode(11, n7, n8);
        TreeNode n3 = new TreeNode(8, n5, n6);
        TreeNode n2 = new TreeNode(4, n4, null);
        TreeNode n1 = new TreeNode(5, n2, n3);

        System.out.println(new MaximumPathSum().maxPathSum(n1));
    }
}
