package com.gemini.leetcode.problems.tree.traverse;

import com.gemini.leetcode.model.TreeNode;
import com.google.gson.Gson;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/
 * 双端队列
 *
 * @Author Gemini
 * 2022-08-24
 **/
public class ZigzagLevelOrderTraversal {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean reverseOrder = false;
        while (!queue.isEmpty()) {
            LinkedList<TreeNode> levelNodeList = new LinkedList<>();
            while (!queue.isEmpty()) levelNodeList.add(queue.poll());

            LinkedList<Integer> levelList = new LinkedList<>();
            while (!levelNodeList.isEmpty()) {
                TreeNode node = levelNodeList.poll();
                if (reverseOrder) levelList.addFirst(node.val);
                else levelList.addLast(node.val);
                if (node.left != null) queue.add(node.left);
                if (node.right != null) queue.add(node.right);
            }

            result.add(levelList);
            reverseOrder = !reverseOrder;
        }

        return result;
    }

    public static void main(String[] args) {
        TreeNode testTree = TreeNode.getTestTree();
        List<List<Integer>> result = new ZigzagLevelOrderTraversal().zigzagLevelOrder(testTree);
        System.out.println(new Gson().toJson(result));
    }
}
