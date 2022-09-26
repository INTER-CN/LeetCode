package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/binary-search-tree-iterator/
 * 二叉树中序遍历
 *
 * @Author Gemini
 * 2022-09-06
 **/
public class BSTIterator {

    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        stack = new Stack<>();
        TreeNode leftNode = root;
        while (leftNode != null) {
            stack.push(leftNode);
            leftNode = leftNode.left;
        }
    }

    public int next() {
        int result;
        TreeNode node = stack.pop();
        result = node.val;

        TreeNode leftNode = node.right;
        while (leftNode != null) {
            stack.push(leftNode);
            leftNode = leftNode.left;
        }

        return result;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }

    public static void main(String[] args) {
        TreeNode n4 = new TreeNode(9);
        TreeNode n5 = new TreeNode(20);
        TreeNode n3 = new TreeNode(15, n4, n5);
        TreeNode n2 = new TreeNode(3);
        TreeNode n1 = new TreeNode(7, n2, n3);
        BSTIterator iterator = new BSTIterator(n1);
        while (iterator.hasNext()) System.out.print(iterator.next() + " ");
        System.out.println();
    }
}
