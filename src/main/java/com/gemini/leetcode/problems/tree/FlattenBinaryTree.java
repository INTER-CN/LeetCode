package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;
import com.gemini.leetcode.problems.tree.traverse.TraverseTool;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/
 * 栈操作
 *
 * @author 天何
 * @date 2022/7/17
 */
public class FlattenBinaryTree {

    public void flatten(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return;

        // 先序遍历栈
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode pre = null, current = null;
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            // visit node
            pre = current;
            current = node;
            if (pre != null && current != null) {
                pre.left = null;
                pre.right = current;
            }

            // visit children
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        current.left = null;
        current.right = null;
    }

    public static void main(String[] args) {
        TreeNode testTree = TraverseTool.getTestTree();
        new FlattenBinaryTree().flatten(testTree);
        System.out.println(testTree);
    }
}
