package com.gemini.leetcode.problems.tree.pathsum;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/path-sum-iii/
 * 二叉树递归
 *
 * @author 天何
 * @date 2022/7/8
 */
public class PathSum3 {

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return targetSum == root.val ? 1 : 0;

        return pathSumWithRoot(root, targetSum) + pathSumWithoutRoot(root, targetSum);
    }

    /**
     * 包含root节点的路径数
     */
    private int pathSumWithRoot(TreeNode root, int targetSum) {
        if (root == null) return targetSum == 0 ? 1 : 0;
        if (root.left == null && root.right == null) return targetSum == root.val ? 1 : 0;

        int pathCount = 0;
        // 只有root
        if (targetSum == root.val) pathCount++;
        // root + 左子树
        if (root.left != null) pathCount += pathSumWithRoot(root.left, targetSum - root.val);
        // root + 右子树
        if (root.right != null) pathCount += pathSumWithRoot(root.right, targetSum - root.val);

        return pathCount;
    }

    /**
     * 不包含root节点的路径数
     */
    private int pathSumWithoutRoot(TreeNode root, int targetSum) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 0;

        int pathCount = 0;
        // 只有左子树
        if (root.left != null) pathCount += pathSum(root.left, targetSum);
        // 只有右子树
        if (root.right != null) pathCount += pathSum(root.right, targetSum);

        return pathCount;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(10);
        TreeNode n2 = new TreeNode(5);
        TreeNode n3 = new TreeNode(-3);
        TreeNode n4 = new TreeNode(3);
        TreeNode n5 = new TreeNode(2);
        TreeNode n6 = new TreeNode(11);
        TreeNode n7 = new TreeNode(3);
        TreeNode n8 = new TreeNode(-2);
        TreeNode n9 = new TreeNode(1);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.right = n6;
        n4.left = n7;
        n4.right = n8;
        n5.right = n9;

        System.out.println(new PathSum3().pathSum(n1, 8));
    }
}
