package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/path-sum/
 * 二叉树递归水题
 *
 * @author 天何
 * @date 2022/7/9
 */
public class PathSum {

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == targetSum;
        return hasPathSum(root.left, targetSum - root.val)
            || hasPathSum(root.right, targetSum - root.val);
    }
}
