package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-search-tree/
 * 二叉搜索树水题
 *
 * @Author Gemini
 * 2022-08-20
 **/
public class LowestCommonAncestorOfBST {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == null || q == null) return null;

        int a = Math.min(p.val, q.val);
        int b = Math.max(p.val, q.val);
        if (a <= root.val && root.val <= b) return root;
        else if (root.val > b) return lowestCommonAncestor(root.left, p, q);
        else return lowestCommonAncestor(root.right, p, q);
    }
}
