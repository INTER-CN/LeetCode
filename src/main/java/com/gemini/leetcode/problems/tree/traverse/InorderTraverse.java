package com.gemini.leetcode.problems.tree.traverse;

import com.gemini.leetcode.model.TreeNode;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/binary-tree-inorder-traversal/
 * 二叉树中序遍历
 *
 * @author 天何
 * @date 2022/7/9
 */
public class InorderTraverse {

    class TraverseState {
        public TreeNode node;
        public boolean traversed;

        public TraverseState(TreeNode node, boolean traversed) {
            this.node = node;
            this.traversed = traversed;
        }
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();

        Stack<TraverseState> stack = new Stack<>();
        stack.push(new TraverseState(root, false));
        List<Integer> list = new ArrayList<>();
        while (!stack.isEmpty()) {
            TraverseState traverseState = stack.pop();
            if (traverseState.traversed) {
                list.add(traverseState.node.val);
                if (traverseState.node.right != null)
                    stack.push(new TraverseState(traverseState.node.right, false));
            } else {
                stack.push(new TraverseState(traverseState.node, true));
                if (traverseState.node.left != null)
                    stack.push(new TraverseState(traverseState.node.left, false));
            }
        }

        return list;
    }

    public static void main(String[] args) {
        TreeNode testTree = TraverseTool.getTestTree();
        List<Integer> result = new InorderTraverse().inorderTraversal(testTree);
        System.out.println(new Gson().toJson(result));
    }
}
