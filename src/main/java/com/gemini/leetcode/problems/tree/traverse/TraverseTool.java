package com.gemini.leetcode.problems.tree.traverse;

import com.gemini.leetcode.model.TreeNode;

/**
 * @author 天何
 * @date 2022/7/9
 */
public class TraverseTool {

    public static TreeNode getTestTree() {
        TreeNode n1 = new TreeNode(1);
        TreeNode n2 = new TreeNode(2);
        TreeNode n3 = new TreeNode(3);
        TreeNode n4 = new TreeNode(4);
        TreeNode n5 = new TreeNode(5);
        TreeNode n6 = new TreeNode(6);
        TreeNode n7 = new TreeNode(7);

        n1.left = n2;
        n1.right = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;

        return n1;
    }

}
