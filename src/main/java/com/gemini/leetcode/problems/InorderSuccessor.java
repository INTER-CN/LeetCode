package com.gemini.leetcode.problems;

import com.gemini.leetcode.model.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/inorder-successor-in-bst/
 * 二叉树遍历
 *
 * @author 天何
 * @date 2022/7/8
 */
public class InorderSuccessor {

    class StackNode {
        public TreeNode node;
        public boolean visitFlag;

        public StackNode(TreeNode node, boolean visitFlag) {
            this.node = node;
            this.visitFlag = visitFlag;
        }
    }

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || root.left == null && root.right == null) return null;

        Stack<StackNode> stack = new Stack<>();
        stack.push(new StackNode(root, false));
        boolean pFlag = false;
        while (!stack.isEmpty()) {
            StackNode stackNode = stack.pop();
            if (!stackNode.visitFlag) {
                stack.push(new StackNode(stackNode.node, true));
                if (stackNode.node.left != null) stack.push(new StackNode(stackNode.node.left, false));
            } else {
                // visit node
                if (pFlag) return stackNode.node;
                if (stackNode.node == p) pFlag = true;

                if (stackNode.node.right != null) stack.push(new StackNode(stackNode.node.right, false));
            }
        }

        return null;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);

        n5.left = n3;
        n5.right = n6;
        n3.left = n2;
        n3.right = n4;
        n2.left = n1;

        System.out.println(new InorderSuccessor().inorderSuccessor(n5, n6).val);
    }
}
