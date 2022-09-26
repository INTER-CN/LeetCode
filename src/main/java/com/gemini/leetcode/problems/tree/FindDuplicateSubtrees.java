package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;

import java.util.*;

/**
 * https://leetcode.cn/problems/find-duplicate-subtrees/
 * 二叉树，递归
 *
 * @Author Gemini
 * 2022-08-26
 **/
public class FindDuplicateSubtrees {

    private Map<TreeNode, Integer> nodeCodeMap;
    private Map<Integer, Integer> duplicateMap;
    private List<TreeNode> result;

    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        nodeCodeMap = new HashMap<>();
        duplicateMap = new HashMap<>();
        result = new LinkedList<>();

        postOrderTraverse(root);

        return result;
    }

    private void postOrderTraverse(TreeNode root) {
        if (root == null) return;
        postOrderTraverse(root.left);
        postOrderTraverse(root.right);

        // visit node
        StringBuilder builder = new StringBuilder();
        builder.append(root.left == null ? "" : ("" + nodeCodeMap.get(root.left)));
        builder.append("|");
        builder.append(root.val);
        builder.append("|");
        builder.append(root.right == null ? "" : ("" + nodeCodeMap.get(root.right)));
        int code = builder.toString().hashCode();
        nodeCodeMap.put(root, code);
        if (duplicateMap.containsKey(code)) {
            int v = duplicateMap.get(code);
            if (v == 1) {
                result.add(root);
                duplicateMap.put(code, 2);
            }
        } else {
            duplicateMap.put(code, 1);
        }
    }

    public static void main(String[] args) {
        TreeNode n7 = new TreeNode(4);
        TreeNode n6 = new TreeNode(4);
        TreeNode n5 = new TreeNode(2, n7, null);
        TreeNode n4 = new TreeNode(4);
        TreeNode n3 = new TreeNode(3, n5, n6);
        TreeNode n2 = new TreeNode(2, n4, null);
        TreeNode n1 = new TreeNode(1, n2, n3);

        List<TreeNode> result = new FindDuplicateSubtrees().findDuplicateSubtrees(n1);
        System.out.println(result);
    }
}
