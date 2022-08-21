package com.gemini.leetcode.problems.list;

import com.gemini.leetcode.model.ListNode;

/**
 * https://leetcode.cn/problems/swap-nodes-in-pairs/
 * 链表操作
 *
 * @Author Gemini
 * 2022-08-18
 **/
public class SwapNodes {

    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode pre = new ListNode(0);
        pre.next = head;

        ListNode p = head, q = head.next, r = pre;
        while (p != null && q != null) {
            r.next = q;
            p.next = q.next;
            q.next = p;
            r = p;
            p = p.next;
            if (p != null) q = p.next;
        }

        return pre.next;
    }

    public static void main(String[] args) {
        ListNode list = ListNode.getTestList();
        ListNode result = new SwapNodes().swapPairs(list);
        ListNode.print(result);
    }
}
