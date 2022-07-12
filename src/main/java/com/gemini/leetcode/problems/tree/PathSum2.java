package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.TreeNode;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.cn/problems/path-sum-ii/
 * 二叉树递归
 *
 * @author 天何
 * @date 2022/7/9
 */
public class PathSum2 {

    public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        if (root == null) return Collections.emptyList();

        int diff = targetSum - root.val;

        if (root.left == null && root.right == null)
            return diff == 0 ? wrapSingleList(root.val) : Collections.emptyList();

        List<List<Integer>> resultList = new ArrayList<>();

        List<List<Integer>> leftList = pathSum(root.left, diff);
        for (List<Integer> listItem : leftList) {
            listItem.add(0, root.val);
            resultList.add(listItem);
        }

        List<List<Integer>> rightList = pathSum(root.right, diff);
        for (List<Integer> listItem : rightList) {
            listItem.add(0, root.val);
            resultList.add(listItem);
        }

        return resultList;
    }

    private List<List<Integer>> wrapSingleList(int val) {
        List<List<Integer>> resultList = new ArrayList<>();
        List<Integer> listItem = new ArrayList<>();
        listItem.add(val);
        resultList.add(listItem);
        return resultList;
    }

    public static void main(String[] args) {
        TreeNode n0 = new TreeNode(5);
        TreeNode n1 = new TreeNode(4);
        TreeNode n2 = new TreeNode(8);
        TreeNode n3 = new TreeNode(11);
        TreeNode n4 = new TreeNode(13);
        TreeNode n5 = new TreeNode(4);
        TreeNode n6 = new TreeNode(7);
        TreeNode n7 = new TreeNode(2);
        TreeNode n8 = new TreeNode(5);
        TreeNode n9 = new TreeNode(1);

        n0.left = n1;
        n0.right = n2;
        n1.left = n3;
        n2.left = n4;
        n2.right = n5;
        n3.left = n6;
        n3.right = n7;
        n5.left = n8;
        n5.right = n9;

        List<List<Integer>> result = new PathSum2().pathSum(n0, 22);
        System.out.println(new Gson().toJson(result));
    }
}
