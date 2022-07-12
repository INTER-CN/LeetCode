package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree-iv/
 * 二叉树最近公共祖先
 * 思路同 {@link LowestCommonAncestor}
 *
 * @author 天何
 * @date 2022/7/9
 */
public class LowestCommonAncestor4 {

    class AncestorData {
        // 包含的目标节点个数
        public int detectCount;
        // 最近公共祖先
        public TreeNode ancestor;

        public AncestorData(int detectCount, TreeNode ancestor) {
            this.detectCount = detectCount;
            this.ancestor = ancestor;
        }
    }

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode[] nodes) {
        AncestorData ancestorData = getAncestorData(root, nodes);
        return ancestorData.detectCount == nodes.length ? ancestorData.ancestor : null;
    }

    private AncestorData getAncestorData(TreeNode root, TreeNode[] nodes) {
        if (root == null) return new AncestorData(0, null);

        AncestorData leftData = getAncestorData(root.left, nodes);
        if (leftData.detectCount == nodes.length) {
            return leftData;
        }

        AncestorData rightData = getAncestorData(root.right, nodes);
        if (rightData.detectCount == nodes.length) {
            return rightData;
        }

        AncestorData data = new AncestorData(leftData.detectCount + rightData.detectCount, root);
        for (TreeNode node : nodes) {
            if (root == node) data.detectCount++;
        }
        return data;
    }
}
