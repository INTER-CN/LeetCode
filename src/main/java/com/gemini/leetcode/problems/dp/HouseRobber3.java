package com.gemini.leetcode.problems.dp;

import com.gemini.leetcode.model.TreeNode;
import com.gemini.leetcode.problems.tree.traverse.TraverseTool;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/house-robber-iii/
 * 二叉树，递归
 *
 * @author 天何
 * @date 2022/7/19
 */
public class HouseRobber3 {

    private Map<TreeNode, Integer> robMap;
    private Map<TreeNode, Integer> robRootMap;
    private Map<TreeNode, Integer> robNoRootMap;

    public int rob(TreeNode root) {
        robMap = new HashMap<>();
        robRootMap = new HashMap<>();
        robNoRootMap = new HashMap<>();

        return robIt(root);
    }

    public int robIt(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;

        if (robMap.containsKey(root)) return robMap.get(root);

        int result = Math.max(robRoot(root), robNoRoot(root));
        robMap.put(root, result);

        return result;
    }

    private int robRoot(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return root.val;

        if (robRootMap.containsKey(root)) return robRootMap.get(root);

        int result = root.val + robNoRoot(root.left) + robNoRoot(root.right);
        robRootMap.put(root, result);

        return result;
    }

    private int robNoRoot(TreeNode root) {
        if (root == null || root.left == null && root.right == null) return 0;

        if (robNoRootMap.containsKey(root)) return robNoRootMap.get(root);

        int result = robIt(root.left) + robIt(root.right);
        robNoRootMap.put(root, result);

        return result;
    }

    public static void main(String[] args) {
        TreeNode testTree = TraverseTool.getTestTree();
        System.out.println(new HouseRobber3().rob(testTree));
    }
}
