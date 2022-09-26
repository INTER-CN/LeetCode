package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/flip-equivalent-binary-trees/
 * 二叉树，递归遍历
 *
 * @Author Gemini
 * 2022-09-01
 **/
public class FlipEquivalentBinaryTrees {

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null) return false;
        if (root1.val != root2.val) return false;
        if (root1.left == null && root1.right == null && root2.left == null && root2.right == null) return true;
        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)
            || flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
    }
}
