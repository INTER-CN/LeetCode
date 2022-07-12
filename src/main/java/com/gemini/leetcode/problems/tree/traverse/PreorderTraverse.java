package com.gemini.leetcode.problems.tree.traverse;

import com.gemini.leetcode.model.TreeNode;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * https://leetcode.cn/problems/binary-tree-preorder-traversal/
 * 二叉树前序遍历
 *
 * @author 天何
 * @date 2022/7/9
 */
public class PreorderTraverse {

    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return Collections.emptyList();

        Stack<TreeNode> stack = new Stack<>();
        List<Integer> list = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            list.add(node.val);
            if (node.right != null) stack.push(node.right);
            if (node.left != null) stack.push(node.left);
        }

        return list;
    }

    public static void main(String[] args) {
        TreeNode testTree = TraverseTool.getTestTree();
        List<Integer> result = new PreorderTraverse().preorderTraversal(testTree);
        System.out.println(new Gson().toJson(result));
    }
}
