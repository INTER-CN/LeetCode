package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/recover-binary-search-tree/
 * 二叉树中序遍历
 *
 * @Author Gemini
 * 2022-08-20
 **/
public class RecoverBST {

    class TraverseNode {
        public TreeNode node;
        public boolean traversed;

        public TraverseNode(TreeNode node, boolean traversed) {
            this.node = node;
            this.traversed = traversed;
        }
    }

    public void recoverTree(TreeNode root) {
        TreeNode target = null, pre, cur = null;

        Stack<TraverseNode> stack = new Stack<>();
        stack.push(new TraverseNode(root, false));
        while (!stack.isEmpty()) {
            TraverseNode tNode = stack.pop();
            if (tNode.traversed) {
                // visit node
                pre = cur;
                cur = tNode.node;
                if (target == null && pre != null && pre.val > cur.val) {
                    target = pre;
                }
                if (target != null && cur.val >= target.val) {
                    // swap the value of target and pre
                    swapValue(target, pre);
                    return;
                }

                if (tNode.node.right != null) stack.push(new TraverseNode(tNode.node.right, false));
            } else {
                tNode.traversed = true;
                stack.push(tNode);
                if (tNode.node.left != null) stack.push(new TraverseNode(tNode.node.left, false));
            }
        }

        // swap the value of target and cur
        swapValue(target, cur);
    }

    private void swapValue(TreeNode node1, TreeNode node2) {
        int temp = node1.val;
        node1.val = node2.val;
        node2.val = temp;
    }

    public static void main(String[] args) {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        n1.left = n2;
        n1.right = n3;
        new RecoverBST().recoverTree(n1);
        System.out.println(n1);
    }
}
