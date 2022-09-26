package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/rotate-list/
 * 链表操作
 *
 * @Author Gemini
 * 2022-09-19
 **/
public class RotateList {

    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null) return head;

        int n = 0;
        ListNode p = head;
        while (p != null) {
            n++;
            p = p.next;
        }

        k = k % n;
        if (k == 0) return head;

        p = head;
        for (int i = 0; i < k; i++) p = p.next;
        ListNode q = head;

        while (p.next != null) {
            p = p.next;
            q = q.next;
        }

        ListNode newHead = q.next;
        p.next = head;
        q.next = null;

        return newHead;
    }
}
