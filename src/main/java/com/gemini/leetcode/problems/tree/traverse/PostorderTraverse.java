package com.gemini.leetcode.problems.tree.traverse;

import com.gemini.leetcode.model.TreeNode;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/binary-tree-postorder-traversal/
 * 二叉树后序遍历
 *
 * @author 天何
 * @date 2022/7/9
 */
public class PostorderTraverse {

    class TraverseState {
        public TreeNode node;
        public boolean traversed;

        public TraverseState(TreeNode node, boolean traversed) {
            this.node = node;
            this.traversed = traversed;
        }
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();

        Stack<TraverseState> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.push(new TraverseState(root, false));
        while (!stack.isEmpty()) {
            TraverseState traverseState = stack.pop();
            if (traverseState.traversed) {
                list.add(traverseState.node.val);
            } else {
                stack.push(new TraverseState(traverseState.node, true));
                if (traverseState.node.right != null)
                    stack.push(new TraverseState(traverseState.node.right, false));
                if (traverseState.node.left != null)
                    stack.push(new TraverseState(traverseState.node.left, false));
            }
        }

        return list;
    }

    public static void main(String[] args) {
        TreeNode testTree = TreeNode.getTestTree();
        List<Integer> result = new PostorderTraverse().postorderTraversal(testTree);
        System.out.println(new Gson().toJson(result));
    }
}
