package com.gemini.leetcode.problems;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/validate-binary-search-tree/
 * 二叉搜索树
 *
 * @author 天何
 * @date 2022/7/7
 */
public class ValidateBinarySearchTree {

    class ValidationResult {
        public boolean isBST;
        public int min;
        public int max;
    }

    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        ValidationResult result = validateBST(root);
        return result.isBST;
    }

    private ValidationResult validateBST(TreeNode root) {
        ValidationResult result = new ValidationResult();

        if (root.left == null && root.right == null) {
            result.isBST = true;
            result.min = root.val;
            result.max = root.val;
        } else if (root.left == null) {
            ValidationResult rightResult = validateBST(root.right);
            result.isBST = rightResult.isBST && root.val < rightResult.min;
            if (result.isBST) {
                result.min = root.val;
                result.max = rightResult.max;
            }
        } else if (root.right == null) {
            ValidationResult leftResult = validateBST(root.left);
            result.isBST = leftResult.isBST && leftResult.max < root.val;
            if (result.isBST) {
                result.min = leftResult.min;
                result.max = root.val;
            }
        } else {
            ValidationResult leftResult = validateBST(root.left);
            ValidationResult rightResult = validateBST(root.right);
            result.isBST = leftResult.isBST && rightResult.isBST
                && leftResult.max < root.val && root.val < rightResult.min;
            if (result.isBST) {
                result.min = leftResult.min;
                result.max = rightResult.max;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(2);
        TreeNode node6 = new TreeNode(6);
        node5.left = node1;
        node5.right = node4;
        node4.left = node3;
        node4.right = node6;

        System.out.println(new ValidateBinarySearchTree().isValidBST(node5));
    }
}
