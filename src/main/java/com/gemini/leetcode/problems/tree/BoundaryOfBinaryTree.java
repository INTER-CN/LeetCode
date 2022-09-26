package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;
import com.google.gson.Gson;

import java.util.*;

/**
 * https://leetcode.cn/problems/boundary-of-binary-tree/
 * 二叉树
 * 按指定规则搜索
 *
 * @Author Gemini
 * 2022-09-02
 **/
public class BoundaryOfBinaryTree {

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        if (root == null) return Collections.emptyList();
        if (root.left == null && root.right == null) return Collections.singletonList(root.val);

        List<Integer> result = new LinkedList<>();
        result.add(root.val);
        result.addAll(collectLeftBoundary(root));
        result.addAll(collectLeaves(root));
        result.addAll(collectReversedRightBoundary(root));

        return result;
    }

    private List<Integer> collectLeaves(TreeNode root) {
        List<Integer> result = new LinkedList<>();

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            if (isLeaf(node)) {
                result.add(node.val);
            } else {
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
            }
        }

        return result;
    }

    private boolean isLeaf(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

    private List<Integer> collectLeftBoundary(TreeNode root) {
        if (root == null || root.left == null) return Collections.emptyList();

        List<Integer> result = new LinkedList<>();

        TreeNode node = root.left;
        while (!isLeaf(node)) {
            result.add(node.val);
            if (node.left != null) node = node.left;
            else node = node.right;
        }

        return result;
    }

    private List<Integer> collectReversedRightBoundary(TreeNode root) {
        if (root == null || root.right == null) return Collections.emptyList();

        Stack<Integer> stack = new Stack<>();

        TreeNode node = root.right;
        while (!isLeaf(node)) {
            stack.push(node.val);
            if (node.right != null) node = node.right;
            else node = node.left;
        }

        List<Integer> result = new LinkedList<>();
        while (!stack.isEmpty()) result.add(stack.pop());

        return result;
    }

    public static void main(String[] args) {
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n3 = new TreeNode(3, n4, null);
        TreeNode n2 = new TreeNode(2, n3, n5);
        TreeNode n7 = new TreeNode(7, null, n6);
        TreeNode n1 = new TreeNode(1, n2, n7);

        List<Integer> result = new BoundaryOfBinaryTree().boundaryOfBinaryTree(n1);
        System.out.println(new Gson().toJson(result));
    }
}
