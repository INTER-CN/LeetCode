package com.gemini.leetcode.problems.easy;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/linked-list-cycle/
 * 链表经典水题
 *
 * @Author Gemini
 * 2022-08-22
 **/
public class LinkedListCycle {

    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode p = head, q = head.next;
        while (p != null && q != null) {
            p = p.next;
            q = q.next;
            if (q == null) break;
            q = q.next;
            if (p == q) return true;
        }
        return false;
    }
}
