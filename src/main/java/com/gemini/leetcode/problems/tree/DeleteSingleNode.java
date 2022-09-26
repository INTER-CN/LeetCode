package com.gemini.leetcode.problems.tree;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/delete-node-in-a-linked-list/
 * 链表水题
 *
 * @Author Gemini
 * 2022-08-31
 **/
public class DeleteSingleNode {

    public void deleteNode(ListNode node) {
        if (node == null || node.next == null) return;

        node.val = node.next.val;
        node.next = node.next.next;
    }
}
