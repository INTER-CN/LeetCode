package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 * https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree-ii/
 * 二叉树最近公共祖先
 * 两道题的题意是一样的，唯一区别在于是否考虑输出为null的情况
 * 递归
 *
 * @author 天何
 * @date 2022/7/9
 */
public class LowestCommonAncestor {

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

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        AncestorData ancestorData = getAncestorData(root, p, q);
        return ancestorData.detectCount == 2 ? ancestorData.ancestor : null;
    }

    private AncestorData getAncestorData(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) return new AncestorData(0, null);

        AncestorData leftData = getAncestorData(root.left, p, q);
        if (leftData.detectCount == 2) {
            return leftData;
        }

        AncestorData rightData = getAncestorData(root.right, p, q);
        if (rightData.detectCount == 2) {
            return rightData;
        }

        AncestorData data = new AncestorData(leftData.detectCount + rightData.detectCount, root);
        if (root == p || root == q) data.detectCount++;
        return data;
    }

    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(0);
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(8);

        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        System.out.println(new LowestCommonAncestor().lowestCommonAncestor(n5, n6, n1).val);
    }
}
