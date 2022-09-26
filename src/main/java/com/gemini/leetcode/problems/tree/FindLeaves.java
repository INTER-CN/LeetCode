package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;
import com.google.gson.Gson;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.cn/problems/find-leaves-of-binary-tree/
 * 二叉树遍历
 *
 * @Author Gemini
 * 2022-08-25
 **/
public class FindLeaves {

    public List<List<Integer>> findLeaves(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;

        while (!isLeave(root)) {
            List<Integer> list = new LinkedList<>();
            collectLeaves(list, root);
            result.add(list);
        }
        result.add(Collections.singletonList(root.val));

        return result;
    }

    private void collectLeaves(List<Integer> list, TreeNode root) {
        if (root == null) return;
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }

        if (root.left != null) {
            if (isLeave(root.left)) {
                list.add(root.left.val);
                root.left = null;
            } else {
                collectLeaves(list, root.left);
            }
        }

        if (root.right != null) {
            if (isLeave(root.right)) {
                list.add(root.right.val);
                root.right = null;
            } else {
                collectLeaves(list, root.right);
            }
        }
    }

    private boolean isLeave(TreeNode node) {
        return node != null && node.left == null && node.right == null;
    }

    public static void main(String[] args) {
        TreeNode testTree = TreeNode.getTestTree();
        List<List<Integer>> result = new FindLeaves().findLeaves(testTree);
        System.out.println(new Gson().toJson(result));
    }
}
