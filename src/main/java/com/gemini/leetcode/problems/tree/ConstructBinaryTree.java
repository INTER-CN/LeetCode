package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/
 * 递归
 *
 * @author 天何
 * @date 2022/7/17
 */
public class ConstructBinaryTree {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int n = preorder.length;
        return buildTree(preorder, 0, n - 1, inorder, 0, n - 1);
    }

    private TreeNode buildTree(int[] preorder, int p1, int p2, int[] inorder, int i1, int i2) {
        if (p1 > p2 || i1 > i2) return null;
        if (p1 == p2 || i1 == i2) return new TreeNode(preorder[p1]);

        int rootVal = preorder[p1];
        int i = i1;
        while (inorder[i] != rootVal) i++;

        TreeNode root = new TreeNode(rootVal);
        int leftCount = i - i1;
        int rightCount = i2 - i;
        root.left = buildTree(preorder, p1 + 1, p1 + leftCount, inorder, i1, i - 1);
        root.right = buildTree(preorder, p2 - rightCount + 1, p2, inorder, i + 1, i2);
        return root;
    }

    public static void main(String[] args) {
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        TreeNode result = new ConstructBinaryTree().buildTree(preorder, inorder);
        System.out.println(result);
    }
}
