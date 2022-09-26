package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * https://leetcode.cn/problems/delete-nodes-and-return-forest/
 * 二叉树，递归
 *
 * @Author Gemini
 * 2022-08-28
 **/
public class DeleteNodesAndReturnForest {

    private List<TreeNode> resultList;
    private Set<Integer> deleteSet;

    public List<TreeNode> delNodes(TreeNode root, int[] toDelete) {
        resultList = new LinkedList<>();
        deleteSet = new HashSet<>();
        for (int v : toDelete) deleteSet.add(v);
        delNodes(root, true, deleteSet);
        if (root.left != null && deleteSet.contains(root.left.val)) root.left = null;
        if (root.right != null && deleteSet.contains(root.right.val)) root.right = null;
        return resultList;
    }

    private void delNodes(TreeNode node, boolean isRoot, Set<Integer> deleteSet) {
        if (node == null) return;

        if (isRoot && !deleteSet.contains(node.val)) resultList.add(node);

        delNodes(node.left, deleteSet.contains(node.val), deleteSet);
        delNodes(node.right, deleteSet.contains(node.val), deleteSet);

        if (node.left != null && deleteSet.contains(node.left.val)) node.left = null;
        if (node.right != null && deleteSet.contains(node.right.val)) node.right = null;
    }

    public static void main(String[] args) {
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3);
        TreeNode n2 = new TreeNode(2, n4, n3);
        TreeNode n1 = new TreeNode(1, n2, null);
        int[] toDelete = {3, 2};
        List<TreeNode> result = new DeleteNodesAndReturnForest().delNodes(n1, toDelete);
        System.out.println(result);
    }
}
