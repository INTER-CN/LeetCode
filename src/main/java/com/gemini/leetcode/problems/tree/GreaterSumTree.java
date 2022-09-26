package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/convert-bst-to-greater-tree/
 * https://leetcode.cn/problems/binary-search-tree-to-greater-sum-tree/
 * 二叉树中序遍历
 *
 * @author 天何
 * @date 2022/7/30
 */
public class GreaterSumTree {

    class TraverseNode {
        public TreeNode node;
        public boolean traversed;

        public TraverseNode(TreeNode node, boolean traversed) {
            this.node = node;
            this.traversed = traversed;
        }
    }

    public TreeNode convertBST(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return root;

        int sum = 0;
        Stack<TraverseNode> stack = new Stack<>();
        stack.push(new TraverseNode(root, false));
        while (!stack.isEmpty()) {
            TraverseNode traverseNode = stack.pop();
            if (traverseNode.traversed) {
                // visit node
                sum += traverseNode.node.val;
                traverseNode.node.val = sum;

                if (traverseNode.node.left != null) {
                    stack.push(new TraverseNode(traverseNode.node.left, false));
                }
            } else {
                stack.push(new TraverseNode(traverseNode.node, true));
                if (traverseNode.node.right != null) {
                    stack.push(new TraverseNode(traverseNode.node.right, false));
                }
            }
        }

        return root;
    }

    public static void main(String[] args) {
        TreeNode testTree = TreeNode.getTestTree();
        TreeNode result = new GreaterSumTree().convertBST(testTree);
        System.out.println(result);
    }
}
