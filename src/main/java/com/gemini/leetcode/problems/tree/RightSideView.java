package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

import java.util.*;

/**
 * https://leetcode.cn/problems/binary-tree-right-side-view/
 * 层次遍历
 *
 * @author 天何
 * @date 2022/7/14
 */
public class RightSideView {

    public List<Integer> rightSideView(TreeNode root) {
        if (root == null) return Collections.emptyList();

        Queue<TreeNode> globalQueue = new LinkedList<>();
        Queue<TreeNode> levelQueue = new LinkedList<>();

        List<Integer> list = new ArrayList<>();
        globalQueue.add(root);
        while (!globalQueue.isEmpty()) {
            while (!globalQueue.isEmpty()) levelQueue.add(globalQueue.poll());
            TreeNode firstNode = levelQueue.poll();
            list.add(firstNode.val);
            if (firstNode.right != null) globalQueue.add(firstNode.right);
            if (firstNode.left != null) globalQueue.add(firstNode.left);
            while (!levelQueue.isEmpty()) {
                TreeNode remainingNode = levelQueue.poll();
                if (remainingNode.right != null) globalQueue.add(remainingNode.right);
                if (remainingNode.left != null) globalQueue.add(remainingNode.left);
            }
        }

        return list;
    }
}
