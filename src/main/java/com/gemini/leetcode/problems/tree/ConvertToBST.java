package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.ListNode;
import com.gemini.leetcode.model.TreeNode;

/**
 * https://leetcode.cn/problems/convert-sorted-list-to-binary-search-tree/
 * 二分法
 *
 * @author 天何
 * @date 2022/7/14
 */
public class ConvertToBST {

    public TreeNode sortedListToBST(ListNode head) {
        if (head == null) return null;

        ListNode scanNode = head;
        int len = 0;
        while (scanNode != null) {
            len++;
            scanNode = scanNode.next;
        }

        return convertToBST(head, len);
    }

    private TreeNode convertToBST(ListNode head, int len) {
        if (len == 0) return null;
        if (len == 1) return new TreeNode(head.val);

        int mid = len / 2;
        ListNode p = head;
        for (int i = 0; i < mid; i++) p = p.next;
        TreeNode root = new TreeNode(p.val);
        root.left = convertToBST(head, mid);
        root.right = convertToBST(p.next, len - mid - 1);

        return root;
    }

    public static void main(String[] args) {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        TreeNode result = new ConvertToBST().sortedListToBST(n1);
        System.out.println(result);
    }
}
