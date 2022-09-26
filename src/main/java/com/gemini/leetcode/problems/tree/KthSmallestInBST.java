package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/kth-smallest-element-in-a-bst/
 * 中序遍历
 *
 * @Author Gemini
 * 2022-08-22
 **/
public class KthSmallestInBST {

    private class TraverseNode {
        public TreeNode node;
        public boolean traversed;

        public TraverseNode(TreeNode node, boolean traversed) {
            this.node = node;
            this.traversed = traversed;
        }
    }

    public int kthSmallest(TreeNode root, int k) {
        Stack<TraverseNode> stack = new Stack<>();
        stack.push(new TraverseNode(root, false));
        int count = 0;
        while (!stack.isEmpty()) {
            TraverseNode tNode = stack.pop();
            if (tNode.traversed) {
                // visit node
                ++count;
                if (count == k) return tNode.node.val;

                if (tNode.node.right != null) stack.push(new TraverseNode(tNode.node.right, false));
            } else {
                tNode.traversed = true;
                stack.push(tNode);
                if (tNode.node.left != null) stack.push(new TraverseNode(tNode.node.left, false));
            }
        }

        return -1;
    }
}
