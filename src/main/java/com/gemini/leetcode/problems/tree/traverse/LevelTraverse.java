package com.gemini.leetcode.problems.tree.traverse;

import com.gemini.leetcode.model.TreeNode;
import com.google.gson.Gson;

import java.util.*;

/**
 * https://leetcode.cn/problems/binary-tree-level-order-traversal/
 * 二叉树层序遍历
 *
 * @author 天何
 * @date 2022/7/9
 */
public class LevelTraverse {

    public List<List<Integer>> levelOrder(TreeNode root) {
        if (root == null) return Collections.emptyList();

        Queue<TreeNode> globalQueue = new LinkedList<>();
        Queue<TreeNode> levelQueue = new LinkedList<>();

        globalQueue.add(root);

        List<List<Integer>> resultList = new ArrayList<>();
        while (!globalQueue.isEmpty()) {
            while (!globalQueue.isEmpty()) levelQueue.add(globalQueue.poll());
            List<Integer> levelList = new ArrayList<>();
            while (!levelQueue.isEmpty()) {
                TreeNode node = levelQueue.poll();
                levelList.add(node.val);
                if (node.left != null) globalQueue.add(node.left);
                if (node.right != null) globalQueue.add(node.right);
            }
            resultList.add(levelList);
        }

        return resultList;
    }

    public static void main(String[] args) {
        TreeNode testTree = TraverseTool.getTestTree();
        List<List<Integer>> result = new LevelTraverse().levelOrder(testTree);
        System.out.println(new Gson().toJson(result));
    }
}
